package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class TypeEtablissement extends AbstractEntity{

    private String code;
    private String lib;
    private String description;
}
