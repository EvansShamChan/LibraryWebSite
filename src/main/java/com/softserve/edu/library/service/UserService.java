package com.softserve.edu.library.service;

import com.softserve.edu.library.dao.UserDao;
import com.softserve.edu.library.dto.LoginDto;
import com.softserve.edu.library.dto.RegisterDto;
import com.softserve.edu.library.entity.User;

public class UserService {
    public String isUserPresent(LoginDto loginDto) {
        UserDao userDao = new UserDao();
        User user = userDao.getUser(loginDto.getUsername(), loginDto.getPassword());
        if (user.getUsername() == null || !(user.getUsername()).equals(loginDto.getUsername())) {
            return "";
        } else {
            if (user.getIsAdmin() == 0) {
                return "user";
            } else if (user.getIsAdmin() == 1) {
                return "admin";
            } else {
                throw new RuntimeException("User not found");
            }
        }
    }

    public boolean isUserPresent(RegisterDto registerDto) {
        UserDao userDao = new UserDao();
        User user = userDao.getUser(registerDto.getUsername(), registerDto.getPassword());
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
