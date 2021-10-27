package com.project.demo.controller.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.demo.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ClientRequest {
    private String name;

    //Creates a Client object.
    @JsonIgnore
    public Client createClient(){
        return Client.builder().name(this.name).build();
    }
}
