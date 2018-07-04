package com.softserve.edu.library.service;

import com.softserve.edu.library.dao.UserDao;
import com.softserve.edu.library.dto.LoginDto;
import com.softserve.edu.library.dto.RegisterDto;
import com.softserve.edu.library.entity.User;

public class UserService {
    public boolean isUserPresent(LoginDto loginDto) {
        UserDao userDao = new UserDao();
        User user = userDao.getUser(loginDto.getUsername(), loginDto.getPassword());
        if (user.getUsername() == null || !(user.getUsername()).equals(loginDto.getUsername())) {
            return false;
        } else {
            return true;
        }
    }

    //todo: delete this shit when merge with vitalik superdao
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
