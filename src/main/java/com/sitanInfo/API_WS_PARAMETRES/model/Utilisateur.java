package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table( schema = "parametres")
@Data
public class Utilisateur extends AbstractEntity{

    private String login;
    private String passwd;
    private String temoinPasswd;
    private String email;
    private String emailReseau;
    private String temoinEmail;
    private String numeroSms;
    private String temoinSms;
    private String validite;
    private Date debutValidite;
    private Date finValidite;
    private Date DCreation;
    private Date DModification;
    private String connexion;
    private Boolean selected;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement")
    private Etablissement etablissement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centres")
    private Centres centres;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personnes")
    private Personnes personnes;
}
