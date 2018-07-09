package com.softserve.edu.library.service;

import com.softserve.edu.library.dao.RecordsDao;
import com.softserve.edu.library.dto.BookTakingDto;

public class BookTakingService {

    public void takeBook(BookTakingDto bookTakingDto) {
        long idBook = bookTakingDto.getIdBook();
        long idUser = bookTakingDto.getIdUser();
        java.sql.Date takeDate = bookTakingDto.getTakingBookDate();
        long available = bookTakingDto.getAvailable();

        RecordsDao recordsDao = new RecordsDao();
        if (recordsDao.takeBook(idUser,idBook,takeDate) && recordsDao.decrementAvailable(idBook,available)) {

        } else {
            throw new RuntimeException("query did not work");
        }
    }

}
