package com.softserve.edu.library.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateService {
    public static Date getCurrentSqlDate() {
        java.util.Date utilDate = new java.util.Date();
        Date sqlDate = new Date(utilDate.getTime());
        return sqlDate;
    }

    public static Date parseStringToSqlDate(String stringDate){
        try {
            Date sqlDate;
            java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
            sqlDate = new Date(utilDate.getTime());
            return sqlDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
