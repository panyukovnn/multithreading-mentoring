package ru.panyukovnn.multithreadingmentoring.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfo {

    private String brand;
    private String name;
    private BigDecimal price;
}