package com.logistic.logisticapi.domain.service;

import com.logistic.logisticapi.domain.exception.ValidacaoDeCadastroException;
import com.logistic.logisticapi.domain.model.Entrega;
import com.logistic.logisticapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EntregaService {

    private EntregaRepository entregaRepository;

    /**
     * Realiza uma verificação para saber se a entrega existe.
     * Se existir ele retorna a entrega, se não existir aciona um erro de validação de cadastro
     * @param entregaId Recebe um id de uma entrega
     * @return retorna uma entrega
     */
    public Entrega buscarEntrega(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new ValidacaoDeCadastroException("Entrega não encontrada")); // -> caso a entrega não exista vai dar erro
    }

    /**
     * Lista todas as entregas existentes
     * @return Retorna uma lista de entregas
     */
    public List<Entrega> listarTodasEntregas() {
        return entregaRepository.findAll();
    }
}
