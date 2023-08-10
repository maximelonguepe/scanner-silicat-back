package com.main.scannersilicatback.controllers;

import com.main.scannersilicatback.entities.Couleur;
import com.main.scannersilicatback.repositories.CouleurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/couleurs")
@CrossOrigin(origins = "*")
public class CouleurController {
    @Autowired
    CouleurRepository couleurRepository;
    @GetMapping
    public List<Couleur> findAll(){
        return couleurRepository.findAll();
    }

    @PostMapping
    public Couleur save(@RequestBody Couleur couleur){
        return couleurRepository.save(couleur);
    }
}
