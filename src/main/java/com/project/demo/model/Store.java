package com.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.demo.controller.Response.StoreResponse;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Movie> movies;

    //Create StoreResponse object
    @JsonIgnore
    public StoreResponse createStoreResponse(){
        return new StoreResponse(this.id, this.name);
    }
}
