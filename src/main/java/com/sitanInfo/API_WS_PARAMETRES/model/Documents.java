package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Documents extends AbstractEntity{

    private String code;
    private String libelle;
    private String description;
    private String fichier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement")
    private Etablissement etablissement;
}