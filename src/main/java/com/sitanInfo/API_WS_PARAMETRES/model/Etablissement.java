package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Etablissement extends AbstractEntity{

    private String code;
    private String nom;
    private String abreviation;
    private String identifiantEtab;
    private String groupe;
    private String ville;
    private String adresse;
    private String tel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeEtablissement")
    private TypeEtablissement typeEtablissement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pays")
    private Pays pays;
}
