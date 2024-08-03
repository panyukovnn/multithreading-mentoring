package ru.panyukovnn.multithreadingmentoring.patterns.parallelrequeset;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.client.ProductInfoClient;
import ru.panyukovnn.multithreadingmentoring.client.RecommendationsClient;
import ru.panyukovnn.multithreadingmentoring.dto.ProductInfo;
import ru.panyukovnn.multithreadingmentoring.dto.ProductPageResponse;
import ru.panyukovnn.multithreadingmentoring.dto.Recommendation;
import ru.panyukovnn.multithreadingmentoring.mapper.ProductMapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParallelRequestService {

    private final ProductMapper productMapper;
    private final ProductInfoClient productInfoClient;
    private final ExecutorService correctElasticExecutor;
    private final RecommendationsClient recommendationsClient;

    public void sequentialExecution() {
        ProductInfo productInfo = productInfoClient.fetchProductInfo();
        List<Recommendation> recommendations = recommendationsClient.fetchRecommendations();

        ProductPageResponse productPage = productMapper.toPage(productInfo, recommendations);

        log.info("Страница продукта: {}", productPage);
    }

    public CompletableFuture<ProductPageResponse> parallelExecution() {
        CompletableFuture<ProductInfo> productInfoCompletableFuture = CompletableFuture.supplyAsync(() -> productInfoClient.fetchProductInfo(), correctElasticExecutor);
        CompletableFuture<List<Recommendation>> recommendationsCompletableFuture = CompletableFuture.supplyAsync(() -> recommendationsClient.fetchRecommendations(), correctElasticExecutor);

        return CompletableFuture.allOf(productInfoCompletableFuture, recommendationsCompletableFuture)
            .thenApply(ignore -> {
                ProductInfo productInfo = productInfoCompletableFuture.getNow(new ProductInfo());
                List<Recommendation> recommendations = recommendationsCompletableFuture.getNow(List.of());

                ProductPageResponse productPage = productMapper.toPage(productInfo, recommendations);

                log.info("Страница продукта: {}", productPage);

                return productPage;
            });
    }
}
