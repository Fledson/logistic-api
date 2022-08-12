package com.logistic.logisticapi.domain.service;

import com.logistic.logisticapi.domain.exception.EntidadeNaoEncontradaException;
import com.logistic.logisticapi.domain.model.Entrega;
import com.logistic.logisticapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private EntregaRepository entregaRepository;

    /**
     * Realiza uma verificação para saber se a entrega existe.
     * Se existir ele retorna a entrega, se não existir aciona um erro de validação de cadastro
     * @param entregaId Recebe um id de uma entrega
     * @return retorna uma entrega
     */
    public Entrega buscar(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada")); // -> caso a entrega não exista vai dar erro
    }

}
