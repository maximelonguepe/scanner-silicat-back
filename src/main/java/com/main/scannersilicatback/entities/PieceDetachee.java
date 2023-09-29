package com.main.scannersilicatback.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class PieceDetachee extends Objet{
    private static final String NAME="PIECE_DETACHEE";

    @OneToMany(mappedBy = "objet",cascade = CascadeType.ALL)
    private List<Couleur> couleurs;

    public PieceDetachee() {
        super();
        setType(NAME);
    }
}
