package com.dh.dentalclinic.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("<h1>Welcome to Dental Clinic System</h1>");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("<h1> Welcome Admin </h1>");
    }
}
