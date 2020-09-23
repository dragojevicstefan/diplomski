package com.example.scooterRent.model;


import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @SQLInjectionSafe
    @Column(unique = true, nullable = false)
    @Email(message = "Email should be valid")
    private String email;

    @SQLInjectionSafe
    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(nullable = false)
    private boolean isAdmin;

    @Column(nullable = false)
    private boolean isBlocked;

    @Column(nullable = false)
    private boolean isActive;
    public User() { /* empty constructor */ }

    public User(String email, String password, boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isBlocked = false;
        this.isActive = false;
    }

    public User(String email, String password, String name, String surname, boolean isAdmin) {
            this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.isAdmin = isAdmin;
        this.isBlocked = false;
        this.isActive = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

