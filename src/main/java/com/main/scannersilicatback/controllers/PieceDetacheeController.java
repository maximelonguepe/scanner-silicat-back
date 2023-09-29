package com.main.scannersilicatback.controllers;

import com.main.scannersilicatback.entities.Accessoire;
import com.main.scannersilicatback.entities.Couleur;
import com.main.scannersilicatback.entities.PieceDetachee;
import com.main.scannersilicatback.repositories.PieceDetacheeRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/piecedetachees")
@CrossOrigin(origins = "*")
public class PieceDetacheeController {
    @Autowired
    private PieceDetacheeRepository pieceDetacheeRepository;

    @GetMapping
    public List<PieceDetachee> findAll(){
        return pieceDetacheeRepository.findAll();
    }

    @PostMapping
    public PieceDetachee save(@RequestBody PieceDetachee pieceDetachee) {
        if (pieceDetachee.getId() == 0) {
            pieceDetachee.setId(null);
        }
        pieceDetachee.getCouleurs().forEach(couleur -> {
            couleur.setObjet(pieceDetachee);
        });
        pieceDetachee.setQuantiteOuMl(pieceDetachee.getCouleurs().stream().mapToDouble(Couleur::getMetreLineaire).reduce(0, Double::sum));
        return pieceDetacheeRepository.save(pieceDetachee);

    }

    @PutMapping
    public PieceDetachee modifyPieceDetachee(@RequestBody PieceDetachee pieceDetachee) {
        pieceDetachee.getCouleurs().forEach(couleur -> {
            couleur.setObjet(pieceDetachee);
        });
        pieceDetachee.setQuantiteOuMl(pieceDetachee.getCouleurs().stream().mapToDouble(Couleur::getMetreLineaire).reduce(0, Double::sum));
        return pieceDetachee;
    }

    @GetMapping("/get")
    public ResponseEntity<PieceDetachee> getPieceDetacheeById(@PathParam("id") Integer id){
        Optional<PieceDetachee> pieceDetacheeOptional = pieceDetacheeRepository.findById(id);
        return pieceDetacheeOptional.map(accessoire -> new ResponseEntity<>(accessoire, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
