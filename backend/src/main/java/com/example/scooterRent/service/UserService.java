package com.example.scooterRent.service;


import com.example.scooterRent.model.User;
import com.example.scooterRent.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public User findUserByEmail(String em){
        return userRepo.findByEmail(em);
    }
    public User save(User user){
        return userRepo.save(user);
    }
    public List<User> findall()
    {
        return userRepo.findAll();
    }

    public User getUser(String us){
        List<User> tmp = findall();
        if(tmp.size() == 0)
            return null;

        for(User u : tmp)
        {
            if(u.getEmail().equals(us))
                return u;
        }

        return null;
    }

    public boolean editUser(User u) {
        List<User> tmp = findall();
        if(tmp.size() == 0)
            return false;

        for(User u1 : tmp)
        {
            if(u.getEmail().equals(u1.getEmail())) {
                u1 = u;
                userRepo.save(u1);
                return true;
            }
        }

        return false;
    }

}
