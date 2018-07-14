package com.softserve.edu.library.dto;

public class DeptorsDto {
    private String firstName;
    private String lastName;
    private String bookName;
    private String takeDate;

    public DeptorsDto(String firstName, String lastName, String bookName, String takeDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookName = bookName;
        this.takeDate = takeDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(String takeDate) {
        this.takeDate = takeDate;
    }
}
