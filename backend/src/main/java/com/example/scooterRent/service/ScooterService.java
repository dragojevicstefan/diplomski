package com.example.scooterRent.service;

import com.example.scooterRent.model.Scooter;
import com.example.scooterRent.repository.ScooterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScooterService {

    @Autowired
    ScooterRepo scooterRepo;

    public Scooter findScooterById(Long id) {
        return scooterRepo.findScooterById(id);
    }
    public List<Scooter> findAll() {
        return  scooterRepo.findAll();
    }

    public Scooter save(Scooter c)
    {
        return  scooterRepo.save(c);
    }

    public boolean addScooter(Scooter c){

        List<Scooter> tmp = findAll();
        if(tmp.size() == 0)
        {
            scooterRepo.save(c);
            return true;
        }
        for(Scooter c1 : tmp)
            if(c1.getTitle().equals(c.getTitle()))
            {
                return  false;
            }
            else
            {
                scooterRepo.save(c);
                return true;
            }

        return false;

    }

    public Scooter getScooter(String title){
        List<Scooter> tmp = findAll();
        if(tmp.size() == 0)
            return null;

        for(Scooter c : tmp)
        {
            if(c.getTitle() == null)
                return null;
            if(c.getTitle().equals(title))
                return c;
        }

        return null;
    }
}
