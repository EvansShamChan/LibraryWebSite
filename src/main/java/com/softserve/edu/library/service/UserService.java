package com.softserve.edu.library.service;

import com.softserve.edu.library.dao.UserDao;
import com.softserve.edu.library.dto.LoginDto;
import com.softserve.edu.library.dto.RegisterDto;
import com.softserve.edu.library.entity.User;

import java.util.LinkedList;
import java.util.List;

public class UserService {
    public String[] isUserPresent(LoginDto loginDto) {
        UserDao userDao = new UserDao();
        User user = userDao.getUser(loginDto.getUsername(), loginDto.getPassword());
        String[] roleAndId = new String[2];
        if (user.getUsername() == null || !(user.getUsername()).equals(loginDto.getUsername())) {
            roleAndId[0] = "";
        } else {
            if (user.getIsAdmin() == 0) {
                roleAndId[0] = "user";
                roleAndId[1] = String.valueOf(user.getId());
            } else if (user.getIsAdmin() == 1) {
                roleAndId[0] = "admin";
                roleAndId[1] = String.valueOf(user.getId());
            } else {
                throw new RuntimeException("User not found");
            }
        }
        return roleAndId;
    }

    public boolean isUserPresent(RegisterDto registerDto) {
        UserDao userDao = new UserDao();
        User user = userDao.getUser(registerDto.getUsername());
        if (user.getUsername() == null || !(user.getUsername()).equals(registerDto.getUsername())) {
            return false;
        } else {
            return true;
        }
    }

    public void addNewUser(RegisterDto registerDto) {
        User user = new User(registerDto);
        UserDao userDao = new UserDao();
        userDao.addUser(user);
    }
}
