package com.example.scooterRent.dto;

public class ScooterDTO {
    private String speedLimit;
    private String maxRange;
    private String maxWeight;
    private String capacity;
    private String price;
    private String title;
    private String description;

    public ScooterDTO(String speedLimit, String maxRange, String maxWeight, String capacity, String price, String title, String description) {
        this.speedLimit = speedLimit;
        this.maxRange = maxRange;
        this.maxWeight = maxWeight;
        this.capacity = capacity;
        this.price = price;
        this.title = title;
        this.description = description;
    }
}
