package com.main.scannersilicatback.controllers;

import com.main.scannersilicatback.entities.Objet;
import com.main.scannersilicatback.repositories.ObjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/objets")
public class ObjetController {
    @Autowired
    private ObjetRepository objetRepository;
    @GetMapping
    public List<Objet> findAll(){
        return objetRepository.findAll();
    }
}
