package com.project.demo.exception;

public class NegativeMovieStockException extends RuntimeException{
    public NegativeMovieStockException(){
        super("Stock is negative.");
    }
    public NegativeMovieStockException(String message){
        super(message);
    }
}
