package com.main.scannersilicatback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Consommable extends Objet{
    private static final String NAME="CONSOMMABLE";

    public Consommable() {
        super();
    }
}