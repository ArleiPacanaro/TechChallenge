package com.fiap.techchallenge.energia.dominio.eletrodomestico.dto.request;

import com.fiap.techchallenge.energia.dominio.eletrodomestico.entitie.Eletrodomestico;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@ApiModel(description = "Representa um objeto de Eletrodomestico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EletrodomesticoRequestDTO {
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

    public Eletrodomestico toEntity() {
        Eletrodomestico eletrodomestico = new Eletrodomestico();

        eletrodomestico.setNome(this.nome);
        eletrodomestico.setModelo(this.modelo);
        eletrodomestico.setPotencia(this.potencia);
        eletrodomestico.setSerialNumber(this.serialNumber);
        eletrodomestico.setIdendereco(this.idendereco);

        return eletrodomestico;
    }

    public void ToMapperEntity(Eletrodomestico eletrodomestico) {

        eletrodomestico.setNome(this.nome);
        eletrodomestico.setModelo(this.modelo);
        eletrodomestico.setPotencia(this.potencia);
        eletrodomestico.setSerialNumber(this.serialNumber);
        eletrodomestico.setIdendereco(this.idendereco);
    }

}
