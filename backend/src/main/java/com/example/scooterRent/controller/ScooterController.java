package com.example.scooterRent.controller;

import com.example.scooterRent.dto.ScooterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ScooterController {



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/scooter/generate")
    public ResponseEntity<String> generateScooter(@RequestBody ScooterDTO scooterDto) {


            return new ResponseEntity<>("Certificate created", HttpStatus.OK);

        return new ResponseEntity<>("Wrong date",HttpStatus.BAD_REQUEST);
    }
}
