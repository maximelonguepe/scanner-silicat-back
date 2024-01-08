package com.main.scannersilicatback.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Profil.class, name = "PROFIL"),
        @JsonSubTypes.Type(value = Consommable.class, name = "CONSOMMABLE"),
        @JsonSubTypes.Type(value = PieceDetachee.class, name = "PIECE_DETACHEE")
})
public class Objet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String referenceProduit;

    @Column
    private String type;

    @Column
    private Double quantiteOuMl;

    @Column
    private Double prixUnitaire;
}
