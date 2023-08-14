package com.main.scannersilicatback.controllers;

import com.main.scannersilicatback.entities.Objet;
import com.main.scannersilicatback.entities.Profil;
import com.main.scannersilicatback.repositories.ProfilRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/profils")
@CrossOrigin(origins = "*")

public class ProfilController {
    @Autowired
    private ProfilRepository profilRepository;

    @GetMapping
    public List<Profil> findAllProfils() {
        return profilRepository.findAll();
    }

    @PostMapping
    public Profil saveProfil(@RequestBody Profil profil) {
        if(profil.getId()==0){
            profil.setId(null);
        }
        profil.getCouleurs().forEach(couleur -> {
                    couleur.setProfil(profil);
                }
        );
        return profilRepository.save(profil);
    }

    @PutMapping
    public Profil modifyProfil(@RequestBody Profil profil) {
        profil.getCouleurs().forEach(couleur -> {
            couleur.setProfil(profil);
                }
        );
        return profilRepository.save(profil);
    }

    @GetMapping("/get")
    public ResponseEntity<Profil> getProfilById(@PathParam("id") Integer id) {
        Optional<Profil> optionalProfil = profilRepository.findById(id);
        return optionalProfil.map(profil -> new ResponseEntity<>(profil, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
