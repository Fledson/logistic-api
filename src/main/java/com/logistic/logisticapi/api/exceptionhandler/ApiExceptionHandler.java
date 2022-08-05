package com.logistic.logisticapi.api.exceptionhandler;

import com.logistic.logisticapi.domain.exception.ValidacaoDeCadastroException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice //anotação para definir a classe controladora de exceptions
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    // substituindo e refazendo o metodo da exception
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        // Criando uma lista de erros mapeados
        List<MensagemDeErro.InfoErro> erros = new ArrayList<>();

        // Iterando sobre os erros e dando forma para a mensagem de erro personalizada
        for (ObjectError erro : ex.getBindingResult().getAllErrors()) {
            // pegando o nome do campo do erro (usando uma conversão para alterar o tipo de informação)
            String nome = ((FieldError) erro).getField();

            // Pegando a mensagem padrão do erro
            //String mensagem = erro.getDefaultMessage();

            // Pegando a mensagem padrão do erro
            String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());

            erros.add(new MensagemDeErro.InfoErro(nome, mensagem));

        }

        var mensagemDeErro = new MensagemDeErro(
                status.value(),
                OffsetDateTime.now(),
                "Um ou mais campos estão preenchidos incorretamente, por favor verifique as informações passadas e envie novamente",
                erros
        );

        return handleExceptionInternal(ex, mensagemDeErro,headers, status, request);
    }

    @ExceptionHandler(ValidacaoDeCadastroException.class)
    public ResponseEntity<Object> handleValidacaoDeCadastroException(ValidacaoDeCadastroException ex, WebRequest request) {
        HttpStatus status = HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value());

        var mensagemDeErro = new MensagemDeErro(
                status.value(),
                OffsetDateTime.now(),
                ex.getMessage()
        );

        return handleExceptionInternal(ex, mensagemDeErro, new HttpHeaders(), status, request);
    }
}
