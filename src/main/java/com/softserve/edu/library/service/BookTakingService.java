package com.softserve.edu.library.service;

import com.softserve.edu.library.dao.RecordsDao;
import com.softserve.edu.library.dto.*;

import java.util.List;

public class BookTakingService {

    public String takeBook(BookTakingDto bookTakingDto) {
        long idBook = bookTakingDto.getIdBook();
        long idUser = bookTakingDto.getIdUser();
        java.sql.Date takeDate = bookTakingDto.getTakingBookDate();
        java.util.Date date = new java.util.Date();
        java.sql.Date returnUntil = new java.sql.Date(date.getTime() + (86_400_000 * 10));
        long available = bookTakingDto.getAvailable();

        RecordsDao recordsDao = new RecordsDao();
        if(recordsDao.isUserAlreadyTakeThisBook(idUser, idBook)) {
            return "You already take this book!";
        }
        if(!recordsDao.isDecrementAvailable(idBook)) {
            return "This book cannot be taken!";
        }
        if (recordsDao.doDecrement(idBook,available) && recordsDao.takeBook(idUser, idBook, takeDate, returnUntil)) {
            return "Book was successfully added!";
        } else {
            throw new RuntimeException("query did not work");
        }
    }

    public List<TakenBookDto> getAllTakenBooksByUserId(long idUser) {
        RecordsDao recordsDao = new RecordsDao();
        return recordsDao.getAllBooksByUserId(idUser);
    }
}
