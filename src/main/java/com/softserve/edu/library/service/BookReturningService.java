package com.softserve.edu.library.service;

import com.softserve.edu.library.dao.RecordsDao;
import com.softserve.edu.library.dto.BookReturningDto;
import com.softserve.edu.library.dto.BookTakingDto;
import com.softserve.edu.library.dto.TakenBookDto;

import java.util.List;

public class BookReturningService {

    public String returnBook(BookReturningDto bookReturningDto) {
        long idBook = bookReturningDto.getIdBook();
        long idUser = bookReturningDto.getIdUser();
        java.sql.Date returnDate = bookReturningDto.getReturnDate();
        boolean returned = bookReturningDto.isReturned();

        RecordsDao recordsDao = new RecordsDao();

        if (recordsDao.doIncrement(idBook) && recordsDao.returnBook(idUser, idBook, returnDate, returned)) {
            return "book was successfully added";
        } else {
            throw new RuntimeException("query did not work");
        }
    }

//    public List<TakenBookDto> getAllTakenBooksByUserId(long idUser) {
//        RecordsDao recordsDao = new RecordsDao();
//        return recordsDao.getAllBooksByUserId(idUser);
//    }
}
