package com.main.scannersilicatback.controllers;

import com.main.scannersilicatback.entities.Consommable;
import com.main.scannersilicatback.repositories.ConsommableRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consommables")
@CrossOrigin(origins = "*")
public class ConsommableController {
    @Autowired
    private ConsommableRepository consommableRepository;
    @GetMapping
    public List<Consommable> findAll(){
        return consommableRepository.findAll();
    }
    @GetMapping("/get")
    public ResponseEntity<Consommable> getConsommableById(@PathParam("id") Integer id){
        Optional<Consommable> consommableOptional= consommableRepository.findById(id);
        return consommableOptional.map(objet -> new ResponseEntity<>(objet, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @PostMapping
    public Consommable save(@RequestBody Consommable consommable){
        return consommableRepository.save(consommable);
    }
}
