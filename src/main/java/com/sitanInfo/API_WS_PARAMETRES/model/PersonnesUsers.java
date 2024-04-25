package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class PersonnesUsers extends AbstractEntity{

    private Date DCreation;
    private Date DModification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personnes")
    private Personnes personnes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement")
    private Etablissement etablissement;
}
