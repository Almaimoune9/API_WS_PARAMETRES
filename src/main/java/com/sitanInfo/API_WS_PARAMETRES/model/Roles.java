package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Roles extends AbstractEntity{

    private String code;
    private String libelle;
    private boolean isActif;
}