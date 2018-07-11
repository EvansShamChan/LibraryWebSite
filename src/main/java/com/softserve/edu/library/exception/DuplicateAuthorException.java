package com.softserve.edu.library.exception;

public class DuplicateAuthorException extends RuntimeException {
    public DuplicateAuthorException(String s) {
        super(s);
    }
}