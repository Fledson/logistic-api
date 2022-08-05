package com.logistic.logisticapi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.logistic.logisticapi.resources.ValidationGroups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid // Cria uma validação nos dados do objeto em cascata
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class)
    @NotNull
    @ManyToOne // muitos para um -> Muitas entregas possuiem um cliente (Para funcionar de forma bidirecional precisa fazer o mesmo no outro)
    private Cliente cliente;

    @Valid
    @NotNull
    @Embedded // usado para abstrair os dados de uma classe em uma mesma tabela -> para funcionar a classe deve ser anotada com @Embeddable
    private Destinatario destinatario;

    @NotNull
    private BigDecimal taxa;

    @Enumerated(EnumType.STRING) // dessa forma será armazenado o nome da constante do enum e não seu numero
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // blindando para que a propriedade não seja escrita via Requisições (SOMENTE LEITURA)
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // blindando para que a propriedade não seja escrita via Requisições (SOMENTE LEITURA)
    private LocalDateTime dataPedido;

    // -> pode ser configurado como ao inves de ignorar lançar uma exception
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // blindando para que a propriedade não seja escrita via Requisições (SOMENTE LEITURA)
    private LocalDateTime dataFinalizacao;

}
