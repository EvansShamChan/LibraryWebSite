package com.softserve.edu.library.dto;

import com.softserve.edu.library.service.DateService;

import java.sql.Date;
import java.util.Objects;

public class BookReturningDto {
    private long idUser;
    private long idBook;
    private boolean returned;
    private Date returnDate;

    public BookReturningDto(long idUser, long idBook) {
        this.idUser = idUser;
        this.idBook = idBook;
        this.returned = true;
        this.returnDate = DateService.getCurrentSqlDate();
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookReturningDto that = (BookReturningDto) o;
        return idUser == that.idUser &&
                idBook == that.idBook &&
                returned == that.returned &&
                Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, idBook, returned, returnDate);
    }

    @Override
    public String toString() {
        return "BookReturningDto{" +
                "idUser=" + idUser +
                ", idBook=" + idBook +
                ", returned=" + returned +
                ", returnDate=" + returnDate +
                '}';
    }
}