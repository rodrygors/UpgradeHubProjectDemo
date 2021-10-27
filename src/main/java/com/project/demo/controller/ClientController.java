package com.project.demo.controller;

import com.project.demo.controller.Request.ClientRequest;
import com.project.demo.controller.Response.ClientResponse;
import com.project.demo.model.Client;
import com.project.demo.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //Creates a List of ClientResponse.
    public List<ClientResponse> createClientResponseList(List<Client> clients){
        List<ClientResponse> clientResponseList = new ArrayList<>();

        if(!clients.isEmpty()){
            for (Client client : clients) {
                clientResponseList.add(client.createClientResponse());
            }
        }
        return clientResponseList;
    }

    @GetMapping(value = "/clients")
    public List<ClientResponse> getAllClients(){
        return createClientResponseList(clientService.findAllClients());
    }

    @GetMapping(value = "/clients/{id}")
    public ClientResponse getClientById(@PathVariable(value = "id") Long id){
        return clientService.findClientById(id).createClientResponse();
    }

    @PostMapping(value = "/clients")
    public ClientResponse createClient(@RequestBody ClientRequest clientRequest){
        return clientService.addClient(clientRequest.createClient()).createClientResponse();
    }

    @PutMapping(value = "/clients/{id}")
    public ClientResponse updateClient(@PathVariable(value = "id") Long id, @RequestBody ClientRequest clientRequest){
        return clientService.updateClient(id, clientRequest.getName()).createClientResponse();
    }

    @PutMapping(value = "/clients/{clientId}/movies/{movieId}")
    public ClientResponse addMovieToClient(
            @PathVariable(value = "clientId") Long clientId,
            @PathVariable(value = "movieId") Long movieId){
        return clientService.addMovieToClient(clientId, movieId).createClientResponse();
    }

    @DeleteMapping(value = "/clients/{id}")
    public void deleteClient(@PathVariable(value = "id") Long id){
        clientService.deleteClient(id);
    }
}
