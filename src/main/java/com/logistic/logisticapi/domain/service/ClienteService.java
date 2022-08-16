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

    /**
     * Usa o repository do JPA para buscar no banco de dados todos os clientes cadastrados
     * @return Retorna um staqtus code 201 se tiver tudo certo e no corpo da requisição uma lista de Clientes
     */
    public List<Cliente> listarClientes() {
        var clientes = repository.findAll();
        return clientes;
    }

    /**
     * Busca um cliente especifico usando o JPA mas aciona um erro caso não exista
     * @param clienteId Id do cliente buscado
     * @return Retorna um objeto do tipo Cliente caso exista
     */
    public Object buscarClientePorID(Long clienteId) {
        // retorno o cliente
        return buscarCliente(clienteId); //200
        /**
         * resposta alternativa, com status code porem sem mensagem de retorno
         * return repository.findById(id)
         * .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
         */
    }

    /**
     * Faz um cadastro de um novo cliente no banco de dados
     * @param cliente Objeto cliente a ser salvo
     * @return Retorna o cliente salvo
     */
    @Transactional // declara que o metodo tem que ser executado dentro de uma transação, se algo der errado a transação é descartada
    public Cliente salvarCliente(Cliente cliente) {

        verSeClienteExistePorNomeEEmail(cliente);

        Cliente clienteSalvo = repository.save(cliente);
        return clienteSalvo;
    }

    /**
     * Atualiza os dados de um determinado cliente repassado pelo ID,
     * @param clienteId Id do cliente que deseja atualizar
     * @param clienteAtualizado Objeto cliente com os novos dados do cliente
     * @return Retorna o cliente atualizado, ha retorno se houver erro (dispara um erro de cliente não encontrado)
     */
    @Transactional
    public Object atualizarDadosDoCliente(Long clienteId, Cliente clienteAtualizado) {

        // verifica se o cliente existe
        if (mensagemDeClienteNaoExiste(clienteId) != null) return mensagemDeClienteNaoExiste(clienteId) ;

        // se existir, configura o id do cliente passado, para o mesmo id do que deseja atualizar
        clienteAtualizado.setId(clienteId);
        // atualiza is dados do cliente
        clienteAtualizado = salvarCliente(clienteAtualizado);

        // retorna o cliente
        return clienteAtualizado;
    }

    /**
     * Deleta o cliente passado
     * @param clienteId Id do cliente que deseja deletar
     * @return Só ha retorno se houver erro (dispara um erro de cliente não encontrado)
     */
    @Transactional
    public Object deletarCliente(Long clienteId) {
        if (mensagemDeClienteNaoExiste(clienteId) != null) return mensagemDeClienteNaoExiste(clienteId);

        repository.deleteById(clienteId);

        return null;
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

    /**
     * Metodo que verifica se um cliente existe no banco com mesmo nome repassado ou o mesmo email
     * @param cliente Cliente a ser verificado
     */
    private void verSeClienteExistePorNomeEEmail(Cliente cliente) {

        // verificando no banco se já existe um cliente com esse email (usando a API steam para percorrer os cliente e fazer uma comparação)
        boolean emailEmUso = repository.findByEmail(cliente.getEmail()).stream().anyMatch(clienteExistenteNoBanco -> !clienteExistenteNoBanco.equals(cliente));
        // verificando no banco se já existe um cliente com esse nome (usando a API steam para percorrer os cliente e fazer uma comparação)
        boolean nomeEmUso = repository.findByNome(cliente.getNome()).stream().anyMatch(clienteExistenteNoBanco -> !clienteExistenteNoBanco.equals(cliente));

        // Verificando e acionando os erros
        if (emailEmUso) {
            throw new ValidacaoDeCadastroException("Já Existe um cliente cadastrado com estes dados de e-mail");
        } else if (nomeEmUso){
            throw new ValidacaoDeCadastroException("Já Existe um cliente cadastrado com este nome");
        }

    }
}
