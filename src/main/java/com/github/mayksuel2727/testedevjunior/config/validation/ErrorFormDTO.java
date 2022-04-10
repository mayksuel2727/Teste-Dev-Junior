package com.github.mayksuel2727.testedevjunior.config.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorFormDTO {

    private String field;
    private String error;

}
