package com.main.scannersilicatback.controllers;

import com.main.scannersilicatback.entities.Objet;
import com.main.scannersilicatback.repositories.ObjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/objets")
public class ObjetController {
    @Autowired
    private ObjetRepository objetRepository;
    @GetMapping
    public List<Objet> findAll(){
        return objetRepository.findAll();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Objet> getObjectById(@PathVariable Integer id) {
        Optional<Objet> objetOptional = objetRepository.findById(id);
        return objetOptional.map(objet -> new ResponseEntity<>(objet, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
