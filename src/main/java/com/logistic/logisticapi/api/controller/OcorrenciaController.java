package com.logistic.logisticapi.api.controller;

import com.logistic.logisticapi.api.assembler.OcorrenciaAssembler;
import com.logistic.logisticapi.api.model.OcorrenciaModel;
import com.logistic.logisticapi.api.model.input.OcorrenciaInput;
import com.logistic.logisticapi.domain.service.BuscaEntregaService;
import com.logistic.logisticapi.domain.service.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias") //-> criando caminho para um sub recurso
public class OcorrenciaController {

    private RegistroOcorrenciaService registroOcorrenciaService;
    private BuscaEntregaService buscaEntregaService;
    private OcorrenciaAssembler ocorrenciaAssembler;

    @GetMapping
    public List<OcorrenciaModel> listarOcorrencias(@PathVariable Long entregaId){
        var entrega = buscaEntregaService.buscar(entregaId);

        return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrarOcorrencia(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput){

        var ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
    }

}
