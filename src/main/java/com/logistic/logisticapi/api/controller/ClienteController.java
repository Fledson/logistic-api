package com.logistic.logisticapi.api.controller;

import com.logistic.logisticapi.domain.model.Cliente;
import com.logistic.logisticapi.domain.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodosOsClientes() {
        return clienteService.listarClientes(); // 200
    }

    @GetMapping("{clienteId}")
    public Object buscarClientePorID(@PathVariable Long clienteId) {
        return clienteService.buscarClientePorID(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // retorna 201
    public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente){
        return clienteService.salvarCliente(cliente);
    }

    @PutMapping("{clienteId}")
    public Object alterarCliente(@PathVariable Long clienteId,@Valid @RequestBody Cliente clienteAtualizacao) {
        return clienteService.atualizarDadosDoCliente(clienteId, clienteAtualizacao);
    }

    @DeleteMapping("{clienteId}")
    public Object deletarCliente(@PathVariable Long clienteId) {
       return clienteService.deletarCliente(clienteId);
    }
}
