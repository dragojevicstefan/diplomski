package com.example.scooterRent.repository;

import com.example.scooterRent.model.RentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRequestRepo extends JpaRepository<RentRequest, Long> {
    List<RentRequest> findAll();
    List<RentRequest> findAllByClientId(Long id);
    RentRequest findRentRequestById(Long id);
}
