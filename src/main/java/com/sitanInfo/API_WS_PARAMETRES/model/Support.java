package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table( schema = "parametres")
@Data
public class Support extends AbstractEntity{

    private String type;
    private String code;
    private String libelle;
    private Boolean etat;
    private String champ1;
    private String champ2;
    private String champ3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement")
    private Etablissement etablissement;
}
