package com.fiap.techchallenge.energia.dominio.eletrodomestico.dto.response;

import com.fiap.techchallenge.energia.dominio.eletrodomestico.entitie.Eletrodomestico;
import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "Representa um objeto de requisicao de eletrodomestico")
public class EletrodomesticoResponseDto {
    public EletrodomesticoResponseDto(Eletrodomestico eletrodomestico) {
        this.id = eletrodomestico.getId();
        this.nome = eletrodomestico.getNome();
        this.modelo = eletrodomestico.getModelo();
        this.potencia = eletrodomestico.getPotencia();
        this.serialNumber = eletrodomestico.getSerialNumber();
        this.endereco = eletrodomestico.getEndereco();
    }

    @ApiModelProperty(value = "ID do cadastro do eletrodomestico", example = "1", position = 1)
    private Long id;

    @ApiModelProperty(value = "Informacao do nome do eletrodomestico", example = "Microondas", position = 1)
    private String nome;

    @ApiModelProperty(value = "Informacao do nome do modelo do eletrodomestico", example = "LG", position = 1)
    private String modelo;

    @ApiModelProperty(value = "Informacao da potencia do eletrodomestico", example = "900W", position = 1)
    private Integer potencia;

    @ApiModelProperty(value = "Informacao do serial number do eletrodomestico", example = "GN20142B530", position = 1)
    private String serialNumber;

    @ApiModelProperty(value = "Endereco no qual o eletrodomestico está", example = "Rua natal luiz Pereira, 274, Carioca, São Lourenço, Minas Gerais", position = 1)
    private Endereco endereco;
}

