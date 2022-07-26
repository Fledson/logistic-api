package com.logistic.logisticapi.api.controller;

import com.logistic.logisticapi.domain.model.Cliente;
import com.logistic.logisticapi.domain.repository.ClienteRepository;
import com.logistic.logisticapi.resources.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        var clientes = repository.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientes); // 200
    }

    @GetMapping("{clienteId}")
    public Object buscarCliente(@PathVariable Long clienteId) {
        // verifica se o cliente existe
        if (mensagemDeClienteNaoExiste(clienteId) != null) return mensagemDeClienteNaoExiste(clienteId) ;

        // se existir, busca o cliente no banco e armazena na variavel
        var clienteBuscado = repository.findById(clienteId);

        // retorno o cliente
        return clienteBuscado;//200
        /**
         * resposta alternativa, com status code porem sem mensagem de retorno
         * return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
         */
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // retorna 201
    public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    @PutMapping("{clienteId}")
    public Object alterarCliente(@PathVariable Long clienteId,@Valid @RequestBody Cliente clienteAtualizacao) {

        // verifica se o cliente existe
        if (mensagemDeClienteNaoExiste(clienteId) != null) return mensagemDeClienteNaoExiste(clienteId) ;

        // se existir, configura o id do cliente passado, para o mesmo id do que deseja atualizar
        clienteAtualizacao.setId(clienteId);
        // atualiza is dados do cliente
        clienteAtualizacao = repository.save(clienteAtualizacao);

        // retorna o cliente com o statusCode 200
        return ResponseEntity.ok(clienteAtualizacao);
    }

    @DeleteMapping("{clienteId}")
    public Object deletarCliente(@PathVariable Long clienteId) {

        if (mensagemDeClienteNaoExiste(clienteId) != null) return mensagemDeClienteNaoExiste(clienteId);

        repository.deleteById(clienteId);

       return ResponseEntity.noContent().build();
    }

    private ResponseEntity<ReturnMessage> mensagemDeClienteNaoExiste(Long clienteId) {
        // verifica se o cliente existe
        if (!repository.existsById(clienteId)) {
            // caso nao exista, retorna uma mensagem personalizada
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(
                            new ReturnMessage(System.currentTimeMillis(),
                                    HttpStatus.NOT_ACCEPTABLE.value(),
                                    "Cliente não encontrado")
                    );
        }
        // se não existir retorna nulo
        return null;
    }
}
