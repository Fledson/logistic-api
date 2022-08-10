package com.logistic.logisticapi.api.model.input;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class EntregaInputModel {

    @Valid // -> cascateamento de validação (realizando a validação de dentro da classe)
    @NotNull
    private ClienteIdInput cliente;

    @Valid
    @NotNull
    private DestinatarioInput destinatario;

    @NotNull
    private BigDecimal taxa;

}
