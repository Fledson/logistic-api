package com.logistic.logisticapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
public class MensagemDeErro {

    private Integer statusCode;

    private OffsetDateTime data;

    private String mensagem;

    private List<InfoErro> dadosDoErro;

    public MensagemDeErro(Integer statusCode, OffsetDateTime data, String mensagem) {
        this.statusCode = statusCode;
        this.data = data;
        this.mensagem = mensagem;
    }

    @Data
    @AllArgsConstructor
    public static class InfoErro {

        private String campoDoErro;

        private String mensagemDoErro;

    }

}
