package com.main.scannersilicatback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Couleur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nomCouleur;

    @Column
    private Double metreLineaire;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "profil_id")
    private Objet objet;
}
