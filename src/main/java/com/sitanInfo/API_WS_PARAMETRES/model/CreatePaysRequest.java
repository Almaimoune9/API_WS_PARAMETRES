package com.sitanInfo.API_WS_PARAMETRES.model;

import lombok.Data;

@Data
public class CreatePaysRequest {

    private Pays pays;
    private String libelle;

}
