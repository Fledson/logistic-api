package com.logistic.logisticapi.domain.service;

import com.logistic.logisticapi.domain.model.Entrega;
import com.logistic.logisticapi.domain.model.Ocorrencia;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

    private BuscaEntregaService buscaEntregaService;

    /**
     * Registra uma nova ocorrencia em uma entrega
     * @param entregaId O id da entrega que deseja adicionar a ocorrencia
     * @param descricao A descrição da ocorrencia que deseja criar
     * @return retorna a entrega criada
     */
    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        // verifica se o id da entrega repassada existe, se existir tras a entrega
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return entrega.adicionarOcorrencia(descricao);

        /**
         * Por ser uma transação, não é necessario usar um metodo "save" pois o jakarta persistence
         * sincroniza as alterações feitas automaticamente quando temos uma transação aberta
         */
    }

}
