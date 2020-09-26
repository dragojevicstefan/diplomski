package com.example.scooterRent.dto;

public class ScooterFilterDTO {
    private String startDate;
    private String endDate;
    private Number id;

    public ScooterFilterDTO() {
    }

    public ScooterFilterDTO(String startDate, String endDate, Number id) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }
}
