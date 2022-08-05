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

    public Entrega buscarEntrega(Long entregaId) {
        return entregaRepository.findById(entregaId).orElseThrow(() -> new ValidacaoDeCadastroException("Entrega não encontrada"));
    }

    public List<Entrega> listarTodasEntregas() {
        return entregaRepository.findAll();
    }
}
