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

    @Override
    public String toString() {
        return "ScooterDTO{" +
                "speedLimit='" + speedLimit + '\'' +
                ", maxRange='" + maxRange + '\'' +
                ", maxWeight='" + maxWeight + '\'' +
                ", capacity='" + capacity + '\'' +
                ", price='" + price + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(String speedLimit) {
        this.speedLimit = speedLimit;
    }

    public String getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(String maxRange) {
        this.maxRange = maxRange;
    }

    public String getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
