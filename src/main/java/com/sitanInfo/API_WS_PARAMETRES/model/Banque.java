package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table( schema = "parametres")
@Data
public class Banque extends AbstractEntity{

    private String code;
    private String nom;
    private String description;
    private Date createDate;
    private Date updateDate;
    private Boolean selected;
    private String adresse;
    private String telephone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement")
    private Etablissement etablissement;
}
