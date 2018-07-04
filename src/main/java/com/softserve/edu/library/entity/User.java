package com.softserve.edu.library.entity;


import com.softserve.edu.library.dto.RegisterDto;
import com.softserve.edu.library.service.DateService;

import java.sql.Date;

public class User {

    private long id;
    private String firstName;
    private String lastName;
    private java.sql.Date dateOfBirth;
    private java.sql.Date registrationDate;
    private String username;
    private String password;

    public User(RegisterDto registerDto) {
        this.firstName = registerDto.getFirstName();
        this.lastName = registerDto.getLastName();
        this.dateOfBirth = registerDto.getDate();
        this.registrationDate = DateService.getCurrentSqlDate();
        this.username = registerDto.getUsername();
        this.password = registerDto.getPassword();
    }

    public User(String firstName, String lastName, Date dateOfBirth, Date registrationDate, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public java.sql.Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(java.sql.Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public java.sql.Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(java.sql.Date registrationDate) {
        this.registrationDate = registrationDate;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
