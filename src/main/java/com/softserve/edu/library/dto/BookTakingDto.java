package com.softserve.edu.library.dto;

import com.softserve.edu.library.service.DateService;

import java.sql.Date;
import java.util.Objects;

public class BookTakingDto {
    private long idUser;
    private long idBook;
    private long available;
    private java.sql.Date takeDate;

    public BookTakingDto(long idUser, long idBook, long available) {
        this.idUser = idUser;
        this.idBook = idBook;
        this.available = available;
        this.takeDate = DateService.getCurrentSqlDate();
    }

    public long getAvailable() {
        return available;
    }

    public void setAvailable(long available) {
        this.available = available;
    }

    public long getIdUser() {
        return idUser;
    }

    public long getIdBook() {
        return idBook;
    }

    public Date getTakingBookDate() {
        return takeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookTakingDto that = (BookTakingDto) o;
        return idUser == that.idUser &&
                idBook == that.idBook &&
                Objects.equals(takeDate, that.takeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idBook, takeDate);
    }

    @Override
    public String toString() {
        return "BookTakingDto{" +
                "idUser=" + idUser +
                ", idBook=" + idBook +
                ", takingBookDate=" + takeDate +
                '}';
    }
}