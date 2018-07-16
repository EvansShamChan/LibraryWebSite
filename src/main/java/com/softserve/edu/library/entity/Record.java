package com.softserve.edu.library.entity;

import java.sql.Date;
import java.util.Objects;

public class Record {

    private long id;
    private long idUser;
    private long idBook;
    private java.sql.Date takeDate;
    private java.sql.Date returnDate;
    private long returned;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public long getReturned() {
        return returned;
    }

    public void setReturned(long returned) {
        this.returned = returned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return id == record.id &&
                idUser == record.idUser &&
                idBook == record.idBook &&
                returned == record.returned &&
                Objects.equals(takeDate, record.takeDate) &&
                Objects.equals(returnDate, record.returnDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idUser, idBook, takeDate, returnDate, returned);
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idBook=" + idBook +
                ", takeDate=" + takeDate +
                ", returnDate=" + returnDate +
                ", returned=" + returned +
                '}';
    }
}