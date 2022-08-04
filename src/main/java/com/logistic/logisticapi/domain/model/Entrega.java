package com.logistic.logisticapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
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

    @ManyToOne // muitos para um -> Muitas entregas possuiem um cliente (Para funcionar de forma bidirecional precisa fazer o mesmo no outro)
    private Cliente cliente;

    @Embedded // usado para abstrair os dados de uma classe em uma mesma tabela -> para funcionar a classe deve ser anotada com @Embeddable
    private Destinatario destinatario;

    private BigDecimal taxaEntrega;

    @Enumerated(EnumType.STRING) // dessa forma será armazenado o nome da constante do enum e não seu numero
    private StatusEntrega status;

    private LocalDateTime dataPedido;

    private LocalDateTime dataFinalizacao;

}
