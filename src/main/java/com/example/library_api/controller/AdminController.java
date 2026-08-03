package com.example.library_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminController {
    @GetMapping("/admin")
    public Map<String, String> adminOnly() {
        return Map.of("message", "Welcome Admin!");
    }
}
