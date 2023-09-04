package com.fiap.techchallenge.energia.dominio.usuario.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @ApiModelProperty(value = "Informacao do nome do usuário", example = "Arlei", position = 1)
    @NotBlank(message="Username e obrigatório")
    private String username;
    @ApiModelProperty(value = "Informacao da senha do usuário", example = "senha", position = 1)
    @NotBlank(message="Senha e obrigatória")
    private String senha;

}
