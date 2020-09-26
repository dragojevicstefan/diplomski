package com.example.scooterRent.repository;

import com.example.scooterRent.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScooterRepo extends JpaRepository<Scooter,Long> {
    Scooter findByTitle(String title);
    List<Scooter> findAll();
    Scooter findScooterById(Long id);
}
