package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class GradeEnseigants extends AbstractEntity{

    private  String code;
    private String libelle;
    private Boolean etat;

}
