package com.main.scannersilicatback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profil extends Objet{

    @Column
    private Long longueur;

    @Column
    private Long prixMetreLineaire;
}
