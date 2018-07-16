package com.softserve.edu.library.entity;

import java.util.Objects;

public class AuthorToBook {

    private long idBook;
    private long idAuthor;
    private long coauthor;

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public long getCoauthor() {
        return coauthor;
    }

    public void setCoauthor(long coauthor) {
        this.coauthor = coauthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorToBook that = (AuthorToBook) o;
        return idBook == that.idBook &&
                idAuthor == that.idAuthor &&
                coauthor == that.coauthor;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idBook, idAuthor, coauthor);
    }

    @Override
    public String toString() {
        return "AuthorToBook{" +
                "idBook=" + idBook +
                ", idAuthor=" + idAuthor +
                ", coauthor=" + coauthor +
                '}';
    }
}