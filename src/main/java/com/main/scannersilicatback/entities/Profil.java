package com.main.scannersilicatback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor

public class Profil extends Objet{
    private static final String NAME="PROFIL";
    @Column
    private Double longueur;

    @Column
    private Double prixMetreLineaire;

    @OneToMany(mappedBy = "profil")
    private List<Couleur> couleurs;
    public Profil() {
        super();
        setType(NAME);
    }
}
