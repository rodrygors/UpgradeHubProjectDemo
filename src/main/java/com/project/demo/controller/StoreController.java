package com.project.demo.controller;

import com.project.demo.service.StoreService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }
}
