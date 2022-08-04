package com.logistic.logisticapi.api.controller;

import com.logistic.logisticapi.domain.model.Entrega;
import com.logistic.logisticapi.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private SolicitacaoEntregaService solicitacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitarEntrega(@RequestBody Entrega entrega) {
        return solicitacaoEntregaService.solicitarNovaEntrega(entrega);
    }

}
