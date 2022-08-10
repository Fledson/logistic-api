package com.logistic.logisticapi.resources;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurando a ModelMapper para que seja gerenciado pelo spring
 * Declara como um componente spring para configuração de Beans (@Configuration)
 * Por ser uma biblioteca de terceiros o spring não gerencia ela por padrão
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Inicializa e configura um Bean que vai ser gerenciado pelo Spring
     * Disponibilizado para realizar injeções em outras classes
     * @return Retona um objeto do tipo ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
