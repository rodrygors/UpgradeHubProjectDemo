package com.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.demo.controller.Response.ClientResponse;
import com.project.demo.controller.Response.MovieResponse;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    private String name;

    private float price;

    private MovieGenre genre;

    private int stock;

    private int availableStock;

    @ManyToOne
    @JoinColumn(name = "id_store")
    private Store store;

    @ManyToMany(mappedBy = "client_movie", cascade = CascadeType.ALL)
    private List<Client> clients = new ArrayList<Client>();

    //Create a ClientResponse object.
    @JsonIgnore
    public MovieResponse createMovieResponse(){
        return new MovieResponse(
                this.id,
                this.name,
                this.price,
                this.genre,
                this.stock,
                this.availableStock,
                this.store.createStoreResponse()
        );
    }
}
