package com.project.demo.service;

import com.project.demo.exception.StoreNotFound;
import com.project.demo.model.Store;
import com.project.demo.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> findAllStores() {
        return storeRepository.findAll();
    }

    public Store findStoreById(Long id) {
        return storeRepository.findById(id).orElseThrow(StoreNotFound::new);
    }

    public Store addStore(Store store) {
        return storeRepository.save(store);
    }

    public Store updateStore(Long id, String name) {
        Store store = this.findStoreById(id);
        store.setName(name);
        return storeRepository.save(store);
    }

    public void deleteStore(Long id) {
        //Check if store exists
        this.findStoreById(id);
        storeRepository.deleteById(id);
    }
}
