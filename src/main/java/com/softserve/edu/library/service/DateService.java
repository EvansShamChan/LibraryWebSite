package com.softserve.edu.library.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateService {
    public static Date getCurrentSqlDate(){
        java.util.Date utilDate = new java.util.Date();
        Date sqlDate = new Date(utilDate.getTime());
        return sqlDate;
    }

    public static Date parseStringToSqlDate(String stringDate) throws ParseException {
        Date sqlDate = null;
        java.util.Date utilDate = new SimpleDateFormat("yyy-MM-dd").parse(stringDate);
        sqlDate = new Date(utilDate.getTime());
        return sqlDate;
    }
}
