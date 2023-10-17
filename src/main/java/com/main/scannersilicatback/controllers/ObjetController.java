package com.main.scannersilicatback.controllers;

import com.main.scannersilicatback.entities.Objet;
import com.main.scannersilicatback.repositories.ConsommableRepository;
import com.main.scannersilicatback.repositories.ObjetRepository;
import com.main.scannersilicatback.repositories.ProfilRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/objets")
public class ObjetController {
    @Autowired
    private ObjetRepository objetRepository;


    @Autowired
    private ConsommableRepository consommableRepository;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public List<Objet> findAll() {
        return objetRepository.findAll();
    }

    @GetMapping("filter")
    public List<Objet> filterObjects(@PathParam("type") String type, @PathParam("id") String id) {
        Integer idInt = 0;
        if (id != null) {
            idInt = Integer.parseInt(id);

        }
        if (type.equals("PROFIL")) {
            if (idInt == 0) {
                return objetRepository.findByType("PROFIL");
            } else {
                return objetRepository.findByTypeAndId("PROFIL", idInt);
            }
        }
        else if (type.equals("CONSOMMABLE")) {
            if (idInt == 0) {
                return objetRepository.findByType("CONSOMMABLE");
            } else {
                return objetRepository.findByTypeAndId("CONSOMMABLE", idInt);
            }
        }
        else if (type.equals("ACCESSOIRE")) {
            if (idInt == 0) {
                return objetRepository.findByType("ACCESSOIRE");
            } else {
                return objetRepository.findByTypeAndId("ACCESSOIRE", idInt);
            }
        }
        else if(idInt!=0){
            List<Objet> objets = new ArrayList<>();
            Optional<Objet> objetOptional = objetRepository.findById(idInt);
            objetOptional.ifPresent(objets::add);
            return objets;

        }
        return objetRepository.findAll();
    }

    @GetMapping("get")
    public ResponseEntity<Objet> getObjectById(@PathParam("id") Integer id) {
        Optional<Objet> objetOptional = objetRepository.findById(id);
        return objetOptional.map(objet -> new ResponseEntity<>(objet, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteObjectById(@PathParam("id") Integer id){
        try{
            objetRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
