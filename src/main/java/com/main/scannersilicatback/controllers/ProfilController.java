package com.main.scannersilicatback.controllers;

import com.main.scannersilicatback.entities.Profil;
import com.main.scannersilicatback.repositories.ProfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api/profils")
public class ProfilController {
    @Autowired
    private ProfilRepository profilRepository;
    @GetMapping
    public List<Profil> getAllProfiles(){
        return profilRepository.findAll();
    }
    @PostMapping
    public Profil saveProfil(@RequestBody Profil profil){
        return profilRepository.save(profil);
    }
}
