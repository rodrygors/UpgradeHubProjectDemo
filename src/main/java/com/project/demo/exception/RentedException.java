package com.project.demo.exception;

public class RentedException extends RuntimeException{
    public RentedException(){
        super("Movie Already Rented.");
    }
    public RentedException(String message){
        super(message);
    }
}
