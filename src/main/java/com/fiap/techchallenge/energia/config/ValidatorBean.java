package com.fiap.techchallenge.energia.config;

import org.springframework.context.annotation.Configuration;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Configuration
public class ValidatorBean {
    public <T> Map<Path, String> validar(T endereco) {
        Set<ConstraintViolation<T>> validacoes
                = Validation.buildDefaultValidatorFactory().getValidator().validate(endereco);

        Map<Path, String> validacoesToMap = validacoes.stream()
                .collect(Collectors.toMap(
                        validacao -> validacao.getPropertyPath(), validate -> validate.getMessage()));

        return validacoesToMap;
    }
}
