package com.project.demo.service;

import com.project.demo.controller.Response.ClientResponse;
import com.project.demo.exception.ClientNotFound;
import com.project.demo.model.Client;
import com.project.demo.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(ClientNotFound::new);
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, String name) {
        Client client = this.findClientById(id);
        client.setName(name);
        return clientRepository.save(client);
    }

    public void deleteClient(Long id){
        //Check if client exists
        this.findClientById(id);
        clientRepository.deleteById(id);
    }
}
