package com.softserve.edu.library.service;

import com.softserve.edu.library.dao.RecordsDao;
import com.softserve.edu.library.dto.*;
import com.softserve.edu.library.entity.Author;
import com.softserve.edu.library.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookTakingService {

    public String takeBook(BookTakingDto bookTakingDto) {
        long idBook = bookTakingDto.getIdBook();
        long idUser = bookTakingDto.getIdUser();
        java.sql.Date takeDate = bookTakingDto.getTakingBookDate();
        long available = bookTakingDto.getAvailable();

        RecordsDao recordsDao = new RecordsDao();
        if(!recordsDao.isDecrementAvailable(idBook)) {
            return "This book cannot be taken";
        }
        if (recordsDao.doDecrement(idBook,available) && recordsDao.takeBook(idUser,idBook,takeDate)) {
            return "book was successfully added";
        } else {
            throw new RuntimeException("query did not work");
        }
    }

    public List<TakenBookDto> getAllTakenBooksByUserId(long idUser) {
        RecordsDao recordsDao = new RecordsDao();
        return recordsDao.getAllBooksByUserId(idUser);
    }
}
