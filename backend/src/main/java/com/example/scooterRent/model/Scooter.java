package com.example.scooterRent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Scooter {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;


    @Column
    private Integer speedLimit;

    @Column
    private Integer capacity;

    @Column
    private Integer maxRange;

    @Column
    private Integer maxWeight;


    @Column
    private Integer price;


    @Column
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "scooter",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Picture> pictureSet;

    //client
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User client;

    @Column
    private String description;

    @Column
    private boolean isActive;

    public Scooter() {
    }

    public Scooter(Integer speedLimit, Integer capacity, Integer maxRange, Integer maxWeight, Integer price, String title, String description) {
        this.speedLimit = speedLimit;
        this.capacity = capacity;
        this.maxRange = maxRange;
        this.maxWeight = maxWeight;
        this.price = price;
        this.title = title;
        this.description = description;
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(Integer speedLimit) {
        this.speedLimit = speedLimit;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(Integer maxRange) {
        this.maxRange = maxRange;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Picture> getPictureSet() {
        return pictureSet;
    }

    public void setPictureSet(Set<Picture> pictureSet) {
        this.pictureSet = pictureSet;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
