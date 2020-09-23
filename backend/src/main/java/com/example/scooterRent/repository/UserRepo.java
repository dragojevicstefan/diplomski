package com.example.scooterRent.repository;


import com.example.scooterRent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
    List<User> findAll();
}
