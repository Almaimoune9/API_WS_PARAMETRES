package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table( schema = "parametres")
@Data
public class Diplome extends AbstractEntity{

    private String code;
    private String lib;
    private String description;
    private Date createDate;
    private Date updateDate;
    private Boolean selected;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement")
    private Etablissement etablissement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mention")
    private Mention mention;
}
