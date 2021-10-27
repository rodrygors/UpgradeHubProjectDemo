package com.project.demo.controller.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.demo.model.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StoreRequest {
    private String name;

    //Creates a Store object.
    @JsonIgnore
    public Store createStore() {
        return Store.builder().name(this.name).movies(new ArrayList<>()).build();
    }
}
