package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table( schema = "parametres")
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
