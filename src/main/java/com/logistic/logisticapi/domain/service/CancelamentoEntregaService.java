package com.logistic.logisticapi.domain.service;

import com.logistic.logisticapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CancelamentoEntregaService {

    private BuscaEntregaService buscaEntregaService;
    private EntregaRepository entregaRepository;

    public void cancelar(Long entregaId) {

        var entrega = buscaEntregaService.buscar(entregaId);

        entrega.cancelar();

        entregaRepository.save(entrega);
    }

}
