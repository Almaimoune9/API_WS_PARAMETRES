package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table( schema = "parametres")
@Data
public class ModeReglement extends AbstractEntity {

    private String libelle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement")
    private Etablissement etablissement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comptes")
    private Comptes comptes;
}
