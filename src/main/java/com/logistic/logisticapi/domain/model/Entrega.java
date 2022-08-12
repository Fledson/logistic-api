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
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    /**
     * as validações estão comentadas devido ao tratamento da validação dos dados já está sendo feito na camanda da api usando o representation model
     * caso for usado outro tipo de entrada de dados sem ser pela api é necessario ativar novamente
     */

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Valid // Cria uma validação nos dados do objeto em cascata
//    @ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class)
//    @NotNull
    @ManyToOne // muitos para um -> Muitas entregas possuiem um cliente (Para funcionar de forma bidirecional precisa fazer o mesmo no outro)
    private Cliente cliente;

//    @Valid
//    @NotNull
    @Embedded // usado para abstrair os dados de uma classe em uma mesma tabela -> para funcionar a classe deve ser anotada com @Embeddable
    private Destinatario destinatario;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL) // -> cascade => quando for adicionado uma nova instancia de ocorrencia durante uma transação a alteração será salva automaticamente sem o metodo save
    private List<Ocorrencia> ocorrencias = new ArrayList<>();

//    @NotNull
    private BigDecimal taxa;

    @Enumerated(EnumType.STRING) // dessa forma será armazenado o nome da constante do enum e não seu numero
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // blindando para que a propriedade não seja escrita via Requisições (SOMENTE LEITURA)
    private StatusEntrega status;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // blindando para que a propriedade não seja escrita via Requisições (SOMENTE LEITURA)
    private OffsetDateTime dataPedido;

    // -> pode ser configurado como ao inves de ignorar lançar uma exception
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // blindando para que a propriedade não seja escrita via Requisições (SOMENTE LEITURA)
    private OffsetDateTime dataFinalizacao;

    /**
     * Adiciona uma nova ocorrencia a lista de ocorrencias da entrega
     * @param descricao Motivo da ocorrencia
     * @return Retorna o objeto da ocorrencia
     */
    public Ocorrencia adicionarOcorrencia(String descricao) {
        // instanciando uma nova ocorrencia
        Ocorrencia ocorrencia = new Ocorrencia();
        // adicionando a descricao
        ocorrencia.setDescricao(descricao);
        // adicionando data e hora para a ocorrencia
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        // passando a propria entrega para a ocorrencia
        ocorrencia.setEntrega(this);

        // chamando a ocorrencia desta classe e adicionando a ocorrencia criada a lista
        this.getOcorrencias().add(ocorrencia);

        // retorna a ocorrencia criada
        return ocorrencia;
    }
}
