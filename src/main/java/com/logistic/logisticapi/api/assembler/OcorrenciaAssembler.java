package com.logistic.logisticapi.api.assembler;

import com.logistic.logisticapi.api.model.OcorrenciaModel;
import com.logistic.logisticapi.domain.model.Ocorrencia;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

    /**
     * Injetando a dependencia do model mapper
     * Model Mapper é uma biblioteca que faz o mapeamento de objetos e transformação de modelo para outro de forma mais facil
     */
    private ModelMapper modelMapper;

    /**
     * Função que converte o objeto ocorerncia do Domain Model para o Ocorrencia Model do Representation Model
     * @param ocorrencia Objeto Ocorrencia do Domain Model
     * @return Retorna um objeto OcorrenciaModel convertido pelo modelMapper
     */
    public OcorrenciaModel toModel (Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaModel.class);
    }

    public List<OcorrenciaModel> toCollectionModel(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream()
                .map(this::toModel)  //-> convertendo cada item da lista para o modelo de representação
                .collect(Collectors.toList()); // -> Reduzindo o Stream para uma coleção (List)
    }

}
