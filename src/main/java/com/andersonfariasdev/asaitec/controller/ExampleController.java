package com.andersonfariasdev.asaitec.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleController {
    @GetMapping
    public ResponseEntity<?> example() {
        return ResponseEntity.ok("example");
    }
}
