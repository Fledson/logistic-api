package com.logistic.logisticapi.api.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class MensagemDeErro {

    private Integer statusCode;
    private LocalDateTime data;
    private String mensagem;
    private List<InfoErro> dadosDoErro;


    @Data
    @AllArgsConstructor
    public static class InfoErro {
        private String campoDoErro;
        private String mensagemDoErro;
    }

}
