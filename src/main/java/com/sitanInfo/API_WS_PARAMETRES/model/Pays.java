package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table( schema = "parametres")
@Data
public class Pays extends AbstractEntity{
    private String code;
    private String nom;
    private String devise;
    private int monnaie_id = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement")
    private Etablissement etablissement;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(value =ConstraintMode.NO_CONSTRAINT ),name = "monnaie_id",referencedColumnName = "id", insertable = false, updatable = false)
    private Monnaie monnaie;
}
