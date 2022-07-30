package com.logistic.logisticapi.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.logistic.logisticapi.domain.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
public class ReturnMessage {

    private Long timestamp;
    private Integer status;
    private String message;

}
