package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import repository.UserRepository;
import dataUser.DataUser;

import java.util.List;

@RestController
@RequestMapping("/api")

public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{id}")
    public ResponseEntity<DataUser> getTutorialById(@PathVariable("id") int id) {
        DataUser dataUser = userRepository.findById(id);

        if (dataUser != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello world!";
    }
}
