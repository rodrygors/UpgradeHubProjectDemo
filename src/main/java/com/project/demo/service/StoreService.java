package com.project.demo.service;

import com.project.demo.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
}
