package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table( schema = "parametres")
@Data
public class TypeSalle extends AbstractEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement")
    private Etablissement etablissement;

    private String code;
    private String libelle;
    private String description;
}
