package ru.panyukovnn.multithreadingmentoring.mapper;

import org.mapstruct.Mapper;
import ru.panyukovnn.multithreadingmentoring.dto.Feedback;
import ru.panyukovnn.multithreadingmentoring.dto.ProductInfo;
import ru.panyukovnn.multithreadingmentoring.dto.ProductPageResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductPageResponse toPage(ProductInfo productInfo, List<Feedback> feedbacks);
}
