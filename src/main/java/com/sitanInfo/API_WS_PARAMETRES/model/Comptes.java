package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Comptes extends AbstractEntity{

    private String code;
    private String numero;
    private String type;
    private String description;
}
