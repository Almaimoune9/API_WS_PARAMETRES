package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table( schema = "parametres")
@Data
public class PersonnesUtilisateur extends AbstractEntity{

    private Date DCreation;
    private Date DModification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personnes")
    private Personnes personnes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement")
    private Etablissement etablissement;
}
