package com.logistic.logisticapi.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable // -> informa que a classe pode ser abstraida em outra classe/tabela
public class Destinatario {

    @Column(name = "destinatario_nome") // -> customizando o nome que sera colocado na tabela do banco
    private String nome;

    @Column(name = "destinatario_logradouro")
    private String logradouro;

    @Column(name = "destinatario_numero")
    private String numero;

    @Column(name = "destinatario_complemento")
    private String complemento;

    @Column(name = "destinatario_bairro")
    private String bairro;

}
