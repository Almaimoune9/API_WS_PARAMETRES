package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table( schema = "parametres")
@Data
public class TypePeriode extends AbstractEntity{

    private String code;
    private String libelle;
    private Integer duree;
    private String typeDuree;
    private String etat;
}
