package com.ing.assignment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @GetMapping("/status")
    public ResponseEntity<String> checkStatus() {

        return ResponseEntity.status(HttpStatus.OK).body("Game is running.");
    }
}
