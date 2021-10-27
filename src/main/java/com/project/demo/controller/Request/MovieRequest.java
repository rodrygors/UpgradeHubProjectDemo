package com.project.demo.controller.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.demo.model.Movie;
import com.project.demo.model.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MovieRequest {
    private String name;

    private float price;

    private MovieGenre genre;

    private int stock;

    private Long storeId;

    //Creates a Movie object without using:
    //  storeId(taken care in service).
    @JsonIgnore
    public Movie createMovie(){
        return Movie.builder()
                .name(this.name)
                .price(this.price)
                .genre(this.genre)
                .stock(this.stock)
                .build();
    }
}
