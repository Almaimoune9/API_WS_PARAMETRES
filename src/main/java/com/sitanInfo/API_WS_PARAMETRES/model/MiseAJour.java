package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table( schema = "parametres")
@Data
public class MiseAJour extends AbstractEntity{

    private String code;
    private String libelle;
    private Date datemaj;
}
