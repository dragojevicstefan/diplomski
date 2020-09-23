package com.example.scooterRent.dto;

public class RegisterAdminDTO {
    private String email;
    private String password1;
    private String password2;
    private String name;
    private String surname;

    public RegisterAdminDTO() {
    }

    public RegisterAdminDTO(String email, String password1, String password2, String name, String surname) {
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.name = name;
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
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
