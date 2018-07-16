package com.softserve.edu.library.dto;

import java.sql.Date;

public class TakenBookDto {

    private long idUser;
    private long idBook;
    private boolean returned;
    private java.sql.Date takeDate;
    private java.sql.Date returnDate;
    private java.sql.Date returnUntil;
    private String bookName;

    public TakenBookDto() {
    }

    public TakenBookDto(long idUser, long idBook, boolean returned, Date takeDate, Date returnDate, java.sql.Date returnUntil, String bookName) {
        this.idUser = idUser;
        this.idBook = idBook;
        this.returned = returned;
        this.takeDate = takeDate;
        this.returnDate = returnDate;
        this.returnUntil = returnUntil;
        this.bookName = bookName;
    }

    public Date getReturnUntil() {
        return returnUntil;
    }

    public void setReturnUntil(Date returnUntil) {
        this.returnUntil = returnUntil;
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}