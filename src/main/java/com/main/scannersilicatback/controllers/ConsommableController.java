package com.main.scannersilicatback.controllers;

import com.main.scannersilicatback.entities.Consommable;
import com.main.scannersilicatback.repositories.ConsommableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping
    public Consommable save(@RequestBody Consommable consommable){
        return consommableRepository.save(consommable);
    }
}
