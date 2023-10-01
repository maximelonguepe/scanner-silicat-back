package com.main.scannersilicatback.controllers;

import com.main.scannersilicatback.entities.Accessoire;
import com.main.scannersilicatback.entities.Couleur;
import com.main.scannersilicatback.entities.Objet;
import com.main.scannersilicatback.entities.Profil;
import com.main.scannersilicatback.repositories.AccessoireRepository;
import com.main.scannersilicatback.repositories.CouleurRepository;
import com.main.scannersilicatback.repositories.ObjetRepository;
import com.main.scannersilicatback.repositories.ProfilRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/couleurs")
@CrossOrigin(origins = "*")
public class CouleurController {
    @Autowired
    CouleurRepository couleurRepository;

    @Autowired
    ObjetRepository objetRepository;

    @Autowired
    ProfilRepository profilRepository;

    @Autowired
    AccessoireRepository accessoireRepository;


    @GetMapping
    public List<Couleur> findAll() {
        return couleurRepository.findAll();
    }

    @PostMapping
    public Couleur save(@RequestBody Couleur couleur) {
        return couleurRepository.save(couleur);
    }

    @DeleteMapping
    public void delete(@PathParam("id") Integer id) {
        Optional<Couleur> couleurOptional = couleurRepository.findById(id);
        couleurRepository.deleteById(id);
        if (couleurOptional.isPresent()){
            Couleur couleur = couleurOptional.get();
            if(couleur.getObjet()!=null){
                Objet objet=couleur.getObjet();
                if(objet.getType().equals("PROFIL")){
                    Optional<Profil> profilOptional = profilRepository.findById(objet.getId());
                    if(profilOptional.isPresent()){
                        Profil profil = profilOptional.get();
                        profil.setQuantiteOuMl(profil.getCouleurs().stream().mapToDouble(Couleur::getMetreLineaire).reduce(0, Double::sum));
                        profilRepository.save(profil);
                    }
                }
                else if(objet.getType().equals("ACCESSOIRE")){
                    Optional<Accessoire> accessoireOptional = accessoireRepository.findById(id);
                    if(accessoireOptional.isPresent()){
                        Accessoire accessoire = accessoireOptional.get();
                        accessoire.setQuantiteOuMl(accessoire.getCouleurs().stream().mapToDouble(Couleur::getMetreLineaire).reduce(0, Double::sum));
                        accessoireRepository.save(accessoire);
                    }
                }
            }
        }
    }
}
