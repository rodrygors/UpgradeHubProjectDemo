package com.project.demo.service;

import com.project.demo.exception.MovieNotFound;
import com.project.demo.exception.NegativeMovieStockException;
import com.project.demo.exception.StoreNotFound;
import com.project.demo.model.Movie;
import com.project.demo.model.Store;
import com.project.demo.repository.MovieRepository;
import com.project.demo.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final StoreRepository storeRepository;

    public MovieService(MovieRepository movieRepository, StoreRepository storeRepository) {
        this.movieRepository = movieRepository;
        this.storeRepository = storeRepository;
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(MovieNotFound::new);
    }

    public Store findStoreById(Long id) {
        return storeRepository.findById(id).orElseThrow(StoreNotFound::new);
    }

    public Movie addMovie(Long storeId, Movie movie) {
        Store store = this.findStoreById(storeId);
        movie.setStore(store);
        //As we're creating a movie the available
        //stock equals the total stock
        movie.setAvailableStock(movie.getStock());
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long movieId, Long storeId, Movie newMovie) {
        Movie movie = this.findMovieById(movieId);
        Store store = this.findStoreById(storeId);

        movie.setName(newMovie.getName());
        movie.setPrice(newMovie.getPrice());
        movie.setGenre(newMovie.getGenre());

        //Calculate the difference between the new and old total stocks
        //and if the newAvailableStock is negative throw a Conflict exception
        int newAvailableStock = movie.getAvailableStock() + (newMovie.getStock() - movie.getStock());
        if (newAvailableStock < 0) {
            throw new NegativeMovieStockException(
                    "New stock for this movie would be negative, current available stock is: "
                            + movie.getAvailableStock()
                            + ".");
        }
        movie.setStock(newMovie.getStock());
        movie.setAvailableStock(newAvailableStock);

        movie.setStore(store);
        movie.setAvailableStock(newAvailableStock);
        return movie;
    }

    public void deleteMovie(Long id) {
        //Check if movie exists
        this.findMovieById(id);
        movieRepository.deleteById(id);
    }
}
