package com.coffee.shop.exception;

import com.project.demo.controller.HttpErrorResponse;
import com.project.demo.exception.ClientNotFound;
import com.project.demo.exception.MovieNotFound;
import com.project.demo.exception.NegativeMovieStockException;
import com.project.demo.exception.StoreNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler({ClientNotFound.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpErrorResponse handleGenericException(ClientNotFound exception) {
        return new HttpErrorResponse(
                404,
                exception.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler({StoreNotFound.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpErrorResponse handleGenericException(StoreNotFound exception) {
        return new HttpErrorResponse(
                404,
                exception.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler({MovieNotFound.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpErrorResponse handleGenericException(MovieNotFound exception) {
        return new HttpErrorResponse(
                404,
                exception.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler({NegativeMovieStockException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public HttpErrorResponse handleGenericException(NegativeMovieStockException exception) {
        return new HttpErrorResponse(
                409,
                exception.getMessage(),
                LocalDateTime.now()
        );
    }
}
