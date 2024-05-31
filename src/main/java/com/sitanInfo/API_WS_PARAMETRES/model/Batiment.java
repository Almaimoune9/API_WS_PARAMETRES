package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table( schema = "parametres")
@Data
public class Batiment extends AbstractEntity implements ExcelExportable{

    private int id;
    private String code;
    private String nom;
    private String description;
    private Date createDate;
    private Date updateDate;
    private Integer type;
    private Boolean selected;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement")
    private Etablissement etablissement;

    @Override
    public String [] getHeaders() {
        return new String[] {"ID", "Code", "Nom"};
    }

    @Override
    public Object[] getData() {
        return new Object[] {id, code, nom};
    }
}
