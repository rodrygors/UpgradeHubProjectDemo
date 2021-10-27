package com.project.demo.controller.Response;

import com.project.demo.model.MovieGenre;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse {
    private Long id;

    private String name;

    private float price;

    private MovieGenre genre;

    private int stock;

    private int availableStock;

    private StoreResponse storeResponse;

    private List<ClientResponse> clientResponseList = new ArrayList<>();
}
