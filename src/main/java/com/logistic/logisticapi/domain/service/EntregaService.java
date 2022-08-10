package com.logistic.logisticapi.domain.service;

import com.logistic.logisticapi.api.assembler.EntregaAssembler;
import com.logistic.logisticapi.api.model.EntregaModel;
import com.logistic.logisticapi.domain.exception.ValidacaoDeCadastroException;
import com.logistic.logisticapi.domain.model.Entrega;
import com.logistic.logisticapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EntregaService {

    private EntregaRepository entregaRepository;
    private EntregaAssembler entregaAssembler; // -> classe que faz a conversão do modelo de dominio para o de representação

    public ResponseEntity<EntregaModel> buscarEntrega(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega))) // -> convertendo o modelo de entrega para o modelo de representação
                .orElseThrow(() -> new ValidacaoDeCadastroException("Entrega não encontrada")); // -> caso a entrega não exista vai dar erro
    }

    public List<EntregaModel> listarTodasEntregas() {
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }
}
