package com.project.demo.service;

import com.project.demo.exception.ClientNotFound;
import com.project.demo.exception.MovieOutOfStockException;
import com.project.demo.exception.RentedException;
import com.project.demo.model.Client;
import com.project.demo.model.Movie;
import com.project.demo.repository.ClientRepository;
import com.project.demo.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final MovieRepository movieRepository;

    public ClientService(ClientRepository clientRepository, MovieRepository movieRepository) {
        this.clientRepository = clientRepository;
        this.movieRepository = movieRepository;
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(ClientNotFound::new);
    }

    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(ClientNotFound::new);
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, String name) {
        Client client = this.findClientById(id);
        client.setName(name);
        return clientRepository.save(client);
    }

    public Client addMovieToClient(Long clientId, Long movieId) {
        Client client = this.findClientById(clientId);
        Movie movie = this.findMovieById(movieId);
        if (!client.getClient_movie().contains(movie)) {
            if (movie.getAvailableStock() == 0) throw new MovieOutOfStockException();

            movie.setAvailableStock(movie.getAvailableStock() - 1);
            movieRepository.save(movie);
            client.getClient_movie().add(movie);
            return clientRepository.save(client);
        } else throw new RentedException("Movie already by this client.");
    }

    public Client removeMovieFromClient(Long clientId, Long movieId) {
        Client client = this.findClientById(clientId);
        Movie movie = this.findMovieById(movieId);
        if (client.getClient_movie().contains(movie)) {
            movie.setAvailableStock(movie.getAvailableStock()+1);
            movieRepository.save(movie);
            client.getClient_movie().remove(movie);
            return clientRepository.save(client);
        } else throw new RentedException("Movie not rented by this client.");
    }

    public void deleteClient(Long id) {
        //Check if client exists
        this.findClientById(id);
        clientRepository.deleteById(id);
    }
}
