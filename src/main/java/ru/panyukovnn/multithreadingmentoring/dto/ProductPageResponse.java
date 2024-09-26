package ru.panyukovnn.multithreadingmentoring.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductPageResponse {

    private String brand;
    private String name;
    private BigDecimal price;
    private List<Feedback> feedbacks;
}
