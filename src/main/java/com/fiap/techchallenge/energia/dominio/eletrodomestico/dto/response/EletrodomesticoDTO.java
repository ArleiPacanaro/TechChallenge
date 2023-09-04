package com.fiap.techchallenge.energia.dominio.eletrodomestico.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.techchallenge.energia.dominio.eletrodomestico.entitie.Eletrodomestico;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EletrodomesticoDTO {
    private Long id;

    @NotBlank(message = "O nome do eletrodomestico nao pode ser vazio ou nulo.")
    @ApiModelProperty(value = "Informacao do nome do eletrodomestico", example = "Microondas", position = 1)
    private String nome;

    @NotBlank(message = "O modelo do eletrodomestico nao pode ser vazio ou nulo.")
    @ApiModelProperty(value = "Informacao do nome do modelo do eletrodomestico", example = "LG", position = 1)
    private String modelo;

    @NotNull(message = "A potencia do eletrodomestico nao pode ser vazio ou nulo.")
    @ApiModelProperty(value = "Informacao da potencia do eletrodomestico", example = "900", position = 1)
    private Integer potencia;

    @NotBlank(message = "O serial number do eletrodomestico nao pode ser vazio ou nulo.")
    @ApiModelProperty(value = "Informacao do serial number do eletrodomestico", example = "GN20142B530", position = 1)
    private String serialNumber;

    @NotNull(message = "O id do endereco deve ser preenchido")
    @ApiModelProperty(value = "Informar o id do endereco onde o eletrodomestico está", example = "1", position = 1)
    private Long idendereco;

    public EletrodomesticoDTO(Eletrodomestico eletrodomestico) {
        this.id = eletrodomestico.getId();
        this.nome = eletrodomestico.getNome();
        this.modelo = eletrodomestico.getModelo();
        this.potencia = eletrodomestico.getPotencia();
        this.serialNumber = eletrodomestico.getSerialNumber();
        this.idendereco = eletrodomestico.getIdendereco();
    }
}
