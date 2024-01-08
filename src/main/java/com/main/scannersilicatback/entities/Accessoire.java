package com.main.scannersilicatback.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Accessoire extends Objet {

    private static final String NAME = "ACCESSOIRE";

    @OneToMany(mappedBy = "objet", cascade = CascadeType.ALL)
    private List<Couleur> couleurs;

    public Accessoire() {
        super();
        setType(NAME);
    }
}
