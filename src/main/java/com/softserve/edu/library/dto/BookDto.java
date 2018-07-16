package com.softserve.edu.library.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookDto {
    private long id;
    private String name;
    private List<AuthorDto> authors = new ArrayList<>(); // avoid NullPE
    private String publicationDate;
    private String available;
    private int numberOfTaken;

    public BookDto(long id, String name, List<AuthorDto> authors, String publicationDate, String available, int numberOfTaken) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.publicationDate = publicationDate;
        this.available = available;
        this.numberOfTaken = numberOfTaken;
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

    public List<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDto> authors) {
        this.authors = authors;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public int getNumberOfTaken() {
        return numberOfTaken;
    }

    public void setNumberOfTaken(int numberOfTaken) {
        this.numberOfTaken = numberOfTaken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(name, bookDto.name) &&
                Objects.equals(authors, bookDto.authors) &&
                Objects.equals(publicationDate, bookDto.publicationDate) &&
                Objects.equals(available, bookDto.available);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, authors, publicationDate, available);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "name='" + name + '\'' +
                ", authors='" + authors + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", available='" + available + '\'' +
                '}';
    }
}