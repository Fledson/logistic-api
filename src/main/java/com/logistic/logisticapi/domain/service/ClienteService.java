package com.logistic.logisticapi.domain.service;

import com.logistic.logisticapi.domain.exception.ValidacaoDeCadastroException;
import com.logistic.logisticapi.domain.model.Cliente;
import com.logistic.logisticapi.domain.repository.ClienteRepository;
import com.logistic.logisticapi.resources.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ResponseEntity<List<Cliente>> listarClientes() {
        var clientes = repository.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientes); // 200
    }

    public Object buscarClientePorID(Long clienteId) {
        // retorno o cliente
        return buscarCliente(clienteId); //200
        /**
         * resposta alternativa, com status code porem sem mensagem de retorno
         * return repository.findById(id)
         * .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
         */
    }

    @Transactional // declara que o metodo tem que ser executado dentro de uma transação, se algo der errado a transação é descartada
    public Cliente salvarCliente(Cliente cliente) {

        verSeClienteExistePorNomeEEmail(cliente);

        Cliente clienteSalvo = repository.save(cliente);
        return clienteSalvo;
    }

    @Transactional
    public Object atualizarDadosDoCliente(Long clienteId, Cliente clienteAtualizado) {

        // verifica se o cliente existe
        if (mensagemDeClienteNaoExiste(clienteId) != null) return mensagemDeClienteNaoExiste(clienteId) ;

        // se existir, configura o id do cliente passado, para o mesmo id do que deseja atualizar
        clienteAtualizado.setId(clienteId);
        // atualiza is dados do cliente
        clienteAtualizado = salvarCliente(clienteAtualizado);

        // retorna o cliente com o statusCode 200
        return ResponseEntity.ok(clienteAtualizado);
    }

    @Transactional
    public Object deletarCliente(Long clienteId) {
        if (mensagemDeClienteNaoExiste(clienteId) != null) return mensagemDeClienteNaoExiste(clienteId);

        repository.deleteById(clienteId);

        return ResponseEntity.noContent().build();
    }

    /**
     * Verifica se o cliente existe no banco caso não exibe para o cliente uma mensagem personalizada se existir retorna nullo
     * @param clienteId
     * @return retorna uma mensagem de cliente não encontrado caso o cliente não exista
     */
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

    /**
     * Busca o cliente e o retorna, caso não exista dispara uma exeption personalizada de validação de cadastro
     * @param clienteId - id do cliente
     * @return - retona o cliente procurado
     */
    public Cliente buscarCliente(Long clienteId) {
        return repository.findById(clienteId).orElseThrow(() -> new ValidacaoDeCadastroException("Cliente não encontrado"));
    }

    private void verSeClienteExistePorNomeEEmail(Cliente cliente) {

        boolean emailEmUso = repository.findByEmail(cliente.getEmail()).stream().anyMatch(clienteExistenteNoBanco -> !clienteExistenteNoBanco.equals(cliente));

        boolean nomeEmUso = repository.findByNome(cliente.getNome()).stream().anyMatch(clienteExistenteNoBanco -> !clienteExistenteNoBanco.equals(cliente));

        if (emailEmUso) {
            throw new ValidacaoDeCadastroException("Já Existe um cliente cadastrado com estes dados de e-mail");
        } else if (nomeEmUso){
            throw new ValidacaoDeCadastroException("Já Existe um cliente cadastrado com este nome");
        }

    }
}
