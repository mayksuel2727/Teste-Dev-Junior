package com.github.mayksuel2727.testedevjunior.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {
    private String token;
    private String type;
    public TokenDTO(String token, String type) {
        this.token = token;
        this.type = type;
    }


}
