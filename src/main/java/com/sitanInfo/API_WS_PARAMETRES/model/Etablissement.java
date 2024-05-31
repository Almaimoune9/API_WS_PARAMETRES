package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table( schema = "parametres")
@Data
public class Etablissement extends AbstractEntity implements ExcelExportable{

    private int id;
    private String code;
    private String nom;
    private String abreviation;
    private String identifiantEtab;
    private String groupe;
    private String ville;
    private String adresse;
    private String tel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeEtablissement")
    private TypeEtablissement typeEtablissement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pays")
    private Pays pays;

    @Override
    public String [] getHeaders() {
        return new String[] {"ID", "Code", "Nom"};
    }

    @Override
    public Object[] getData() {
        return new Object[] {id, code, nom};
    }
}
