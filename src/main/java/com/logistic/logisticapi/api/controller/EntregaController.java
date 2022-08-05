package com.logistic.logisticapi.api.controller;

import com.logistic.logisticapi.domain.exception.ValidacaoDeCadastroException;
import com.logistic.logisticapi.domain.model.Cliente;
import com.logistic.logisticapi.domain.model.Entrega;
import com.logistic.logisticapi.domain.repository.EntregaRepository;
import com.logistic.logisticapi.domain.service.EntregaService;
import com.logistic.logisticapi.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaRepository entregaRepository;
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private EntregaService entregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitarEntrega(@Valid @RequestBody Entrega entrega) {
        return solicitacaoEntregaService.solicitarNovaEntrega(entrega);
    }

    @GetMapping
    public List<Entrega> listarEntregas() {
        return entregaService.listarTodasEntregas();
    }

    @GetMapping("/{entregaId}")
    public Entrega listarEntrega(@PathVariable Long entregaId){
        var entrega = entregaService.buscarEntrega(entregaId);

        return entrega;
    }


}
