package com.softserve.edu.library.entity;

import java.util.Objects;

public class Book {

    private long id;
    private String name;
    private String publicationDate;
    private long available;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }


    public long getAvailable() {
        return available;
    }

    public void setAvailable(long available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                available == book.available &&
                Objects.equals(name, book.name) &&
                Objects.equals(publicationDate, book.publicationDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, publicationDate, available);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", available=" + available +
                '}';
    }
}