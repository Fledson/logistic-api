package com.logistic.logisticapi.api.assembler;

import com.logistic.logisticapi.api.model.EntregaModel;
import com.logistic.logisticapi.api.model.input.EntregaInputModel;
import com.logistic.logisticapi.domain.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsavel por realizar a conversão de Classes do Domain Model para o Representation Model
 * Aqui está sendo usado o Model Mapper, centralizando para que a API não fique dependente dessa biblioteca
 * caso necessario mudar, realizar a mudança nesta classe (Ver classe de ModelMapperConfig)
 */
@AllArgsConstructor
@Component
public class EntregaAssembler {

    /**
     * Injetando a dependencia do model mapper
     * Model Mapper é uma biblioteca que faz o mapeamento de objetos e transformação de modelo para outro de forma mais facil
     */
    private ModelMapper modelMapper;

    /**
     * Função que converte um objeto da classe Entrega do Domain Model para EntregaModel do Representation Model
     * @param entrega Objeto do Domain Model (Classe bruta que interage com o banco)
     * @return Retorna um objeto do Modelo de Representação de Entrega (Representation Model)
     */
    public EntregaModel toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaModel.class);
    }

    /**
     * Converte uma lista do modelo de dominio para uma lista do modelo de representação
     * @param entregas recebe uma lista de Entrega
     * @return retorna uma lista convertida para EntregaModel
     */
    public List<EntregaModel> toCollectionModel (List<Entrega> entregas) {
        return entregas.stream()
                        .map(this::toModel) //-> convertendo cada item da lista para o modelo de representação
                        .collect(Collectors.toList()); // -> Reduzindo o Stream para uma coleção (List)
    }

    /**
     * Realiza a transformação de uma Modelo de Representação de entrada para um Modelo de Dominio
     * @param entregaInput Recebe um objeto do tipo EntregaInputModel (Modelo de Entrada de Entrega)
     * @return retorna um Modelo de Entrega do Domain Model
     */
    public Entrega toEntity(EntregaInputModel entregaInput) {
        return modelMapper.map(entregaInput, Entrega.class);
    }

}
