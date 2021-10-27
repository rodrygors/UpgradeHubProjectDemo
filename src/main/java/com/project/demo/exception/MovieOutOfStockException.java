package com.project.demo.exception;

public class MovieOutOfStockException extends RuntimeException{
    public MovieOutOfStockException(){
        super("Movie out of stock.");
    }
    public MovieOutOfStockException(String message){
        super(message);
    }
}
