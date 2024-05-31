package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table( schema = "parametres")
@Data
public class Mention extends AbstractEntity{

    private String code;
    private String note;
    private String libelle;

}
