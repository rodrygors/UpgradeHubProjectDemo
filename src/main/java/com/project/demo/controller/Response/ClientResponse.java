package com.project.demo.controller.Response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponse {
    private Long id;

    private String name;

    private List<MovieResponse> movieResponseList = new ArrayList<>();
}
