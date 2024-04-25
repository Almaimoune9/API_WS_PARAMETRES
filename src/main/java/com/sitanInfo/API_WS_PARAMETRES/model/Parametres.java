package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Parametres extends AbstractEntity{

    private String nom;
    private String valeur;
    private String description;
    private String format;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement")
    private Etablissement etablissement;
}
