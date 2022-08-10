package com.logistic.logisticapi.domain.service;

import com.logistic.logisticapi.api.assembler.EntregaAssembler;
import com.logistic.logisticapi.api.model.EntregaModel;
import com.logistic.logisticapi.domain.model.Entrega;
import com.logistic.logisticapi.domain.model.StatusEntrega;
import com.logistic.logisticapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

/**
 * Classe criada com objetivo de separar melhor a regra de negócio especifica para criação de novas entregas
 * caso for expandir, a aplicação estar bem segmentada
 */
@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private EntregaRepository entregaRepository;
    private ClienteService clienteService;
    private EntregaAssembler entregaAssembler; // -> classe que faz a conversão do modelo de dominio para o de representação

    @Transactional
    public EntregaModel solicitarNovaEntrega (Entrega entrega) {

        // REGRA DE NEGOCIO AQUI
        /**
         * EXEMPOS - REGRA DE REGRAS DE NEGOCIOS QUE PODEM SER IMPLEMENTADAS
         * - LIMITAÇÃO DE HORARIOS - Determinar que novas entregras só possam ser efetuadas em determinados horarios (08:00 as 18:00)
         * - DISPONIBILIDADE DE ENTREGA: Verificar se há entregadores disponiveis antes do agendamento (entregas rapidas)
        */

        // retorna um optional do cliente ouu aciona um erro de cliente não encontrado
        var clienteEntrega = clienteService.buscarCliente(entrega.getCliente().getId());

        entrega.setCliente(clienteEntrega);
        entrega.setStatus(StatusEntrega.PENDENTE); // -> por padrão toda entrega assim que iniciada é pendente
        entrega.setDataPedido(OffsetDateTime.now()); // -> data do pedido pega a data da solicitaçaõ da entrega

        return entregaAssembler.toModel(entregaRepository.save(entrega));
    }

}
