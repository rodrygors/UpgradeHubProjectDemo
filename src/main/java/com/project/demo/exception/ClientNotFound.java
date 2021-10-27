package com.project.demo.exception;

public class ClientNotFound extends RuntimeException {
    public ClientNotFound() {
        super("Client Not Found.");
    }

    public ClientNotFound(String message) {
        super(message);
    }
}