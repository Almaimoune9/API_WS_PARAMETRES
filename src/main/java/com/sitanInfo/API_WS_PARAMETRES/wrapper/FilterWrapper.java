package com.sitanInfo.API_WS_PARAMETRES.wrapper;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FilterWrapper {

    private String code;
    private String nom;
    private String description;
    private String tel;
}
