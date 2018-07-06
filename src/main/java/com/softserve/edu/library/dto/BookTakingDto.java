package com.softserve.edu.library.dto;

import com.softserve.edu.library.service.DateService;

import java.sql.Date;
import java.util.Objects;

public class BookTakingDto {
    private long idUser;
    private long idBook;
    private java.sql.Date takingBookDate;

    public BookTakingDto(long idUser, long idBook) {
        this.idUser = idUser;
        this.idBook = idBook;
        this.takingBookDate = DateService.getCurrentSqlDate();;
    }

    public BookTakingDto() {
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

    public Date getTakingBookDate() {
        return takingBookDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookTakingDto that = (BookTakingDto) o;
        return idUser == that.idUser &&
                idBook == that.idBook &&
                Objects.equals(takingBookDate, that.takingBookDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idBook, takingBookDate);
    }

    @Override
    public String toString() {
        return "BookTakingDto{" +
                "idUser=" + idUser +
                ", idBook=" + idBook +
                ", takingBookDate=" + takingBookDate +
                '}';
    }
}
