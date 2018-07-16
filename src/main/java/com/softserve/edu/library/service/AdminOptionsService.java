package com.softserve.edu.library.service;

import com.softserve.edu.library.dao.UserDao;
import com.softserve.edu.library.dto.DeptorsDto;
import com.softserve.edu.library.dto.EachUserInfoDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminOptionsService {
    UserDao dao = new UserDao();

    public Map<String, Integer> getUserAvgStatistic() {
        Map<String, Integer> avgStatistic = new HashMap<>();
        avgStatistic.put("avgAge", dao.getAvgAge());
        avgStatistic.put("avgLibraryUsage", dao.getAvgLibraryUsage());
        avgStatistic.put("avgCountOfAppeal", dao.getAvgCountOfAppeal());
        return avgStatistic;
    }

    public List<DeptorsDto> getDeptors() {
        List<DeptorsDto> deptorsDtoList = dao.getDeptors();
        return deptorsDtoList;
    }

    public List<EachUserInfoDto> getUserInfo() {
        List<EachUserInfoDto> userInfoDtoList = dao.userInfo();
        return userInfoDtoList;
    }
}