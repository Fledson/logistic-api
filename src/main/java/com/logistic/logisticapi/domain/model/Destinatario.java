package com.logistic.logisticapi.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Data
@Embeddable // -> informa que a classe pode ser abstraida em outra classe/tabela
public class Destinatario {

    @NotBlank
    @Column(name = "destinatario_nome") // -> customizando o nome que sera colocado na tabela do banco
    private String nome;

    @NotBlank
    @Column(name = "destinatario_logradouro")
    private String logradouro;

    @NotBlank
    @Column(name = "destinatario_numero")
    private String numero;

    @NotBlank
    @Column(name = "destinatario_complemento")
    private String complemento;

    @NotBlank
    @Column(name = "destinatario_bairro")
    private String bairro;

}
