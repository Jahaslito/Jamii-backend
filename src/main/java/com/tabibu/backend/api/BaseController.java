package com.tabibu.backend.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping("/error")
    public ResponseEntity<String> genericErrorHandler() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Invalid endpoint");
    }
}
