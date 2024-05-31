package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table( schema = "parametres")
@Data
public class Monnaie extends AbstractEntity{

    private String code;
    private String libelle;
    private String taux;
    private String precision;
}
