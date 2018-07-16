package com.softserve.edu.library.dto;

import com.softserve.edu.library.service.DateService;

import java.sql.Date;
import java.util.Objects;

public class BookTakingDto {
    private long idUser;
    private long idBook;
    private long available;
    private java.sql.Date takeDate;
    private java.sql.Date returnUntil;

    public BookTakingDto(long idUser, long idBook, long available) {
        this.idUser = idUser;
        this.idBook = idBook;
        this.available = available;
        this.takeDate = DateService.getCurrentSqlDate();
        this.returnUntil = returnUntilMethod();
    }

    private java.sql.Date returnUntilMethod() {
        java.util.Date date = new java.util.Date();
        return new java.sql.Date(date.getTime() + (86_400_000 * 10));
    }

    public Date getReturnUntil() {
        return returnUntil;
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