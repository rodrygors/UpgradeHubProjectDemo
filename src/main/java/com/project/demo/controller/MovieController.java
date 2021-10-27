package com.project.demo.controller;

import com.project.demo.controller.Request.MovieRequest;
import com.project.demo.controller.Response.MovieResponse;
import com.project.demo.model.Movie;
import com.project.demo.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //Creates a List of MovieResponse.
    public List<MovieResponse> createMovieResponseList(List<Movie> movies){
        List<MovieResponse> movieResponseList = new ArrayList<>();

        if(!movies.isEmpty()){
            for (Movie movie : movies) {
                movieResponseList.add(movie.createMovieResponse());
            }
        }
        return movieResponseList;
    }

    @GetMapping(value = "/movies")
    public List<MovieResponse> getAllMovies(){
        return createMovieResponseList(movieService.findAllMovies());
    }

    @GetMapping(value = "/movies/{id}")
    public MovieResponse getMovieById(@PathVariable(value = "id") Long id){
        return movieService.findMovieById(id).createMovieResponse();
    }

    @PostMapping(value = "/movies")
    public MovieResponse createMovie(@RequestBody MovieRequest movieRequest){
        return movieService.addMovie(movieRequest.getStoreId(), movieRequest.createMovie()).createMovieResponse();
    }

    @PutMapping(value = "/movies/{id}")
    public MovieResponse updateMovie(@PathVariable(value = "id") Long id, @RequestBody MovieRequest movieRequest){
        return movieService.updateMovie(id, movieRequest.getStoreId(), movieRequest.createMovie()).createMovieResponse();
    }

    @DeleteMapping(value = "/movies/{id}")
    public void deleteMovie(@PathVariable(value = "id") Long id){
        movieService.deleteMovie(id);
    }
}
