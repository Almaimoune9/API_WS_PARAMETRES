package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table( schema = "parametres")
@Data
public class Personnes extends AbstractEntity{

    private String code;
    private String nom;
    private String prenom;
    private String sexe;
    private String pere;
    private String mere;
    private Date dateNaissance;
    private String villeNaissance;
    private String nationnalite;
    private String photo;
    private Boolean listeRouge;
    private String adrVille;
    private String adrQuartier;
    private String adrAdresse;
    private String adrFax;
    private String adrTel1;
    private String adrTel2;
    private String adrEmail;
    private String adrBp;
    private Boolean estNeVers;
    private String neVers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "support")
    private Support support;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "civilite")
    private Civilite civilite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement")
    private Etablissement etablissement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "situationFamiliale")
    private SituationFamiliale situationFamiliale;
}
