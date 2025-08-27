package ru.panyukovnn.multithreadingmentoring.patterns.collectparallelrequests;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.client.FeedbacksClient;
import ru.panyukovnn.multithreadingmentoring.client.ProductInfoClient;
import ru.panyukovnn.multithreadingmentoring.dto.Feedback;
import ru.panyukovnn.multithreadingmentoring.dto.ProductInfo;
import ru.panyukovnn.multithreadingmentoring.dto.ProductPageResponse;
import ru.panyukovnn.multithreadingmentoring.mapper.ProductMapper;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParallelRequestService {

    private final ProductMapper productMapper;
    private final ProductInfoClient productInfoClient;
    @Qualifier("feedbacksElasticExecutor")
    private final ExecutorService productInfoElasticExecutor;
    @Qualifier("feedbacksElasticExecutor")
    private final ExecutorService feedbacksElasticExecutor;
    private final FeedbacksClient feedbacksClient;

    public ProductPageResponse sequentialExecution(UUID productId) {
        ProductInfo productInfo = productInfoClient.fetchProductInfo(productId);
        List<Feedback> feedbacks = feedbacksClient.fetchFeedbacks(productId);

        ProductPageResponse productPage = productMapper.toPage(productInfo, feedbacks);

        log.info("Страница продукта: {}", productPage);

        return productPage;
    }

    public CompletableFuture<ProductPageResponse> parallelExecution(UUID productId) {
        CompletableFuture<ProductInfo> productInfoCompletableFuture = CompletableFuture.supplyAsync(() -> productInfoClient.fetchProductInfo(productId), productInfoElasticExecutor);
        CompletableFuture<List<Feedback>> feedbacksCompletableFuture = CompletableFuture.supplyAsync(() -> feedbacksClient.fetchFeedbacks(productId), feedbacksElasticExecutor);

        return CompletableFuture.allOf(productInfoCompletableFuture, feedbacksCompletableFuture)
            .thenApply(ignore -> {
                ProductInfo productInfo = productInfoCompletableFuture.getNow(new ProductInfo());
                List<Feedback> feedbacks = feedbacksCompletableFuture.getNow(List.of());

                ProductPageResponse productPage = productMapper.toPage(productInfo, feedbacks);

                log.info("Страница продукта: {}", productPage);

                return productPage;
            });
    }
}
