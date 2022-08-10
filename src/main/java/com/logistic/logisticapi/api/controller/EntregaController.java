package com.logistic.logisticapi.api.controller;

import com.logistic.logisticapi.api.assembler.EntregaAssembler;
import com.logistic.logisticapi.api.model.EntregaModel;
import com.logistic.logisticapi.api.model.input.EntregaInputModel;
import com.logistic.logisticapi.domain.model.Entrega;
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

    private SolicitacaoEntregaService solicitacaoEntregaService;
    private EntregaService entregaService;
    private EntregaAssembler entregaAssembler; // -> classe que faz a conversão do modelo de dominio para o de representação

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitarEntrega(@Valid @RequestBody EntregaInputModel entregaInput) {

        // convertendo o modelo de entrada para um modelo do dominio
        Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
        // passando o modelo de dominio convertido para ser tratado no service do domain
        Entrega entregaSolicitada = solicitacaoEntregaService.solicitarNovaEntrega(novaEntrega);
        // retorna um modelo de representação da entrega
        return entregaAssembler.toModel(entregaSolicitada);

    }

    @GetMapping
    public List<EntregaModel> listarEntregas() {
        return entregaAssembler.toCollectionModel(entregaService.listarTodasEntregas());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModel> listarEntrega(@PathVariable Long entregaId){
        var entrega = entregaAssembler.toModel(entregaService.buscarEntrega(entregaId));
        return ResponseEntity.ok(entrega);
    }


}
