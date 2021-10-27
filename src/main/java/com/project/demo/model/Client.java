package com.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.demo.controller.Request.ClientRequest;
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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "client_movie",
            joinColumns = @JoinColumn(name = "clientId"),
            inverseJoinColumns = @JoinColumn(name = "movieId"))
    private List<Movie> client_movie = new ArrayList<>();

    //Create a ClientResponse object.
    @JsonIgnore
    public ClientResponse createClientResponse(){
        List<MovieResponse> movieResponseList = new ArrayList<>();
        if (!this.client_movie.isEmpty()){
            for(Movie movie : this.client_movie){
                movieResponseList.add(movie.createMovieResponse());
            }
        }
        return new ClientResponse(
                this.id,
                this.name,
                movieResponseList
        );
    }
}
