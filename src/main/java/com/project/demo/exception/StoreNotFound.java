package com.project.demo.exception;

public class StoreNotFound extends RuntimeException {
    public StoreNotFound() {
        super("Store Not Found.");
    }

    public StoreNotFound(String message) {
        super(message);
    }
}