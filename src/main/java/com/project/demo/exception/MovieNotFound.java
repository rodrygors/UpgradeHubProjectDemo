package com.project.demo.exception;

public class MovieNotFound extends RuntimeException {
    public MovieNotFound() {
        super("Movie Not Found.");
    }

    public MovieNotFound(String message) {
        super(message);
    }
}