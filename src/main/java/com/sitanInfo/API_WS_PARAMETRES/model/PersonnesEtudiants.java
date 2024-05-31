package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table( schema = "parametres")
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
