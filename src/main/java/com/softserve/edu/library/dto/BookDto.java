package com.softserve.edu.library.dto;

import java.util.Objects;

public class BookDto {
    private String title;
    private String author;
    private String publicationDate;
    private String availability;

    public BookDto(String title, String author, String publicationDate, String availability) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.availability = availability;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return Objects.equals(title, bookDto.title) &&
                Objects.equals(author, bookDto.author) &&
                Objects.equals(publicationDate, bookDto.publicationDate) &&
                Objects.equals(availability, bookDto.availability);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, author, publicationDate, availability);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}
