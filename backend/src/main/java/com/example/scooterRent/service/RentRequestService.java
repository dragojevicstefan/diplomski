package com.example.scooterRent.service;

import com.example.scooterRent.model.RentRequest;
import com.example.scooterRent.repository.RentRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@EnableScheduling
public class RentRequestService {

    @Autowired
    RentRequestRepo rentRequestRepo;

    public void addRent(RentRequest rent){
        rentRequestRepo.save(rent);
    }

    public List<RentRequest> findAll() {
        return rentRequestRepo.findAll();
    }

    public List<RentRequest> findAllByClientId(Long id) {
        return rentRequestRepo.findAllByClientId(id);
    }
    public RentRequest findById(Long id) {
        return rentRequestRepo.findRentRequestById(id);
    }
}
