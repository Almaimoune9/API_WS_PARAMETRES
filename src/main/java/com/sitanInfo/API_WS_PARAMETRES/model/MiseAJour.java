package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class MiseAJour extends AbstractEntity{

    private String code;
    private String libelle;
    private Date datemaj;
}
