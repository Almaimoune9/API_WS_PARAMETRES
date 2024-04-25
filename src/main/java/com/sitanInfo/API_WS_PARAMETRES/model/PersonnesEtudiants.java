package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class PersonnesEtudiants extends AbstractEntity{

    private Long etablissement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personnes")
    private Personnes personnes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "support")
    private Support support;
}
