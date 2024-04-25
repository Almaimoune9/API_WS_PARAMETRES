package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Mention extends AbstractEntity{

    private String code;
    private String note;
    private String libelle;

}
