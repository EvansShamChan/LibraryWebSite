package com.softserve.edu.library.entity;

import java.util.List;
import java.util.Objects;

public class Book {

    private long id;
    private String name;
    private String publicationDate;
    private long available;
    private List<Author> authors;

    public Book(long id, String name, String publicationDate, long available, List<Author> authors) {
        this.id = id;
        this.name = name;
        this.publicationDate = publicationDate;
        this.available = available;
        this.authors = authors;
    }

    public Book() {
    }

    public Book(int id) {
        this.id = id;
    }

    public Book(String name) {
        this.name = name;
    }

    public Book(int id, String name, String publicationDate, long available) {
        this(name, publicationDate, available);
        this.id = id;
    }

    public Book(String name, String publicationDate, long available) {
        this.name = name;
        this.publicationDate = publicationDate;
        this.available = available;
    }

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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", available=" + available +
                ", authors=" + authors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                available == book.available &&
                Objects.equals(name, book.name) &&
                Objects.equals(publicationDate, book.publicationDate) &&
                Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, publicationDate, available, authors);
    }
}