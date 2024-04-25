package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class TypeFormation extends AbstractEntity{

    private String code;
    private String libelle;
    private boolean etat;
}
