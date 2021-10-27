package com.project.demo.controller;

import com.project.demo.controller.Request.StoreRequest;
import com.project.demo.controller.Response.StoreResponse;
import com.project.demo.model.Store;
import com.project.demo.service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    //Creates a List of StoreResponse.
    public List<StoreResponse> createStoreResponseList(List<Store> stores){
        List<StoreResponse> storeResponseList = new ArrayList<>();

        if(!stores.isEmpty()){
            for (Store store : stores) {
                storeResponseList.add(store.createStoreResponse());
            }
        }
        return storeResponseList;
    }

    @GetMapping(value = "/stores")
    public List<StoreResponse> getAllStores(){
        return createStoreResponseList(storeService.findAllStores());
    }

    @GetMapping(value = "/stores/{id}")
    public StoreResponse getStoreById(@PathVariable(value = "id") Long id){
        return storeService.findStoreById(id).createStoreResponse();
    }

    @PostMapping(value = "/stores")
    public StoreResponse createStore(@RequestBody StoreRequest storeRequest){
        return storeService.addStore(storeRequest.createStore()).createStoreResponse();
    }

    @PutMapping(value = "/stores/{id}")
    public StoreResponse updateStore(@PathVariable(value = "id") Long id, @RequestBody StoreRequest storeRequest){
        return storeService.updateStore(id, storeRequest.getName()).createStoreResponse();
    }

    @DeleteMapping(value = "/stores/{id}")
    public void deleteStore(@PathVariable(value = "id") Long id){
        storeService.deleteStore(id);
    }
}
