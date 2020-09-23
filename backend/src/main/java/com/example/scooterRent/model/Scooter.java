package com.example.scooterRent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Scooter {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date startOfAd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date endOfAd;

    @Column
    private String description;

    @Column
    private boolean isActive;
}
