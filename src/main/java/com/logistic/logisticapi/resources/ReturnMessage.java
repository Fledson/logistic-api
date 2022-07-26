package com.logistic.logisticapi.resources;

import com.logistic.logisticapi.domain.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnMessage {

    private Long timestamp;
    private Integer status;
    private String message;

}
