package com.softserve.edu.library.dto;

import java.util.Objects;

public class BookDto {
    private String name;
    private String author;
    private String publicationDate;
    private String availability;

    public BookDto(String name, String author, String publicationDate, String availability) {
        this.name = name;
        this.author = author;
        this.publicationDate = publicationDate;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(name, bookDto.name) &&
                Objects.equals(author, bookDto.author) &&
                Objects.equals(publicationDate, bookDto.publicationDate) &&
                Objects.equals(availability, bookDto.availability);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, author, publicationDate, availability);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}

