package com.logistic.logisticapi.api.model.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DestinatarioInput {

    @NotBlank
    private String nome;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String numero;
    @NotBlank
    private String complemento;
    @NotBlank
    private String bairro;

}
