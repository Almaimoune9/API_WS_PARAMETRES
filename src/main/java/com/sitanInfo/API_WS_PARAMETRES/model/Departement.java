package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Departement extends AbstractEntity{


    private String code;
    private String nom;
    private String description;
    private String tel;

}
