package com.logistic.logisticapi.api.assembler;

import com.logistic.logisticapi.api.model.EntregaModel;
import com.logistic.logisticapi.domain.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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

}
