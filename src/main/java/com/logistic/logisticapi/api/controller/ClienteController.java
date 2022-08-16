package com.logistic.logisticapi.api.controller;

import com.logistic.logisticapi.domain.model.Cliente;
import com.logistic.logisticapi.domain.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Classe controladora do fluxo de dados dos clientes
 */
@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService clienteService;

    /**
     * Busca todos os clientes cadastrados
     * @return Retorna uma Lista de Clientes
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK) // retorna 201
    public List<Cliente> listarTodosOsClientes() {
        return clienteService.listarClientes(); // 200
    }

    /**
     * Busca um cliente especifico no banco de dados
     * @param clienteId Id do cliente que deseja buscar
     * @return Retorna um Objeto do cliente
     * Dispara um erro caso o cliente não exista
     */
    @GetMapping("{clienteId}")
    public Object buscarClientePorID(@PathVariable Long clienteId) {
        return clienteService.buscarClientePorID(clienteId);
    }

    /**
     * Cria um novo cliente no banco de dados
     * @param cliente
     * @return Retorna um objeto com os dados do cleinte
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // retorna 201
    public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente){
        return clienteService.salvarCliente(cliente);
    }

    @PutMapping("{clienteId}")
    @ResponseStatus(HttpStatus.OK) // retorna 201
    public Object alterarCliente(@PathVariable Long clienteId,@Valid @RequestBody Cliente clienteAtualizacao) {
        return clienteService.atualizarDadosDoCliente(clienteId, clienteAtualizacao);
    }

    @DeleteMapping("{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deletarCliente(@PathVariable Long clienteId) {
       return clienteService.deletarCliente(clienteId);
    }
}
