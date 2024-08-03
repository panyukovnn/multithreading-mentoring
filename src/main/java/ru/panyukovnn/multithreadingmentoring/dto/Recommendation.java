package ru.panyukovnn.multithreadingmentoring.dto;

import lombok.Data;

@Data
public class Recommendation {

    private int reviewRating;
    private String text;
}