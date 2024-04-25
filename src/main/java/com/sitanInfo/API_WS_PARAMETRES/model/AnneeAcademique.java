package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class AnneeAcademique extends AbstractEntity{


    private String anneeAcademique;
    private Date dateDeb;
    private Date dateFin;
    private String objectif;
    private Boolean isActif;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "etablissement")
    private Etablissement etablissement;
}
