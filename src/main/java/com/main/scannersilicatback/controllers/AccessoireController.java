package com.main.scannersilicatback.controllers;

import com.main.scannersilicatback.entities.Accessoire;
import com.main.scannersilicatback.entities.Couleur;
import com.main.scannersilicatback.repositories.AccessoireRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/accessoires")
@CrossOrigin(origins = "*")
public class AccessoireController {
    @Autowired
    private AccessoireRepository accessoireRepository;

    @GetMapping
    public List<Accessoire> findAll() {
        return accessoireRepository.findAll();
    }

    @PostMapping
    public Accessoire save(@RequestBody Accessoire accessoire) {
        if (accessoire.getId() == 0) {
            accessoire.setId(null);
        }
        accessoire.getCouleurs().forEach(couleur -> {
            couleur.setObjet(accessoire);
        });
        accessoire.setQuantiteOuMl(accessoire.getCouleurs().stream().mapToDouble(Couleur::getMetreLineaire).reduce(0, Double::sum));
        return accessoireRepository.save(accessoire);
    }

    @PutMapping
    public Accessoire modifyAccessoire(@RequestBody Accessoire accessoire) {
        accessoire.getCouleurs().forEach(couleur -> {
            couleur.setObjet(accessoire);
        });
        accessoire.setQuantiteOuMl(accessoire.getCouleurs().stream().mapToDouble(Couleur::getMetreLineaire).reduce(0, Double::sum));
        return accessoire;
    }

    @GetMapping("/get")
    public ResponseEntity<Accessoire> getAccessoireById(@PathParam("id") Integer id){
        Optional<Accessoire> accessoireOptional = accessoireRepository.findById(id);
        return accessoireOptional.map(accessoire -> new ResponseEntity<>(accessoire, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
