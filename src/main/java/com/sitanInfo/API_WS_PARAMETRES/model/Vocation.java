package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table( schema = "parametres")
@Data
public class Vocation extends AbstractEntity{

    private String code;
    private String libelle;
    private String description;
    private Boolean etat;


}
