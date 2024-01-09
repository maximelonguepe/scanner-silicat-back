package com.main.scannersilicatback.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class PieceDetachee extends Objet{
    @OneToMany(mappedBy = "objet",cascade = CascadeType.ALL)
    private List<Couleur> couleurs;

    public PieceDetachee() {
        super();
    }
}
