package com.fiap.techchallenge.energia.dominio.usuario.dto.request;

import com.fiap.techchallenge.energia.dominio.usuario.entitie.Usuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@ApiModel(description = "Representa um objeto de  Usuários")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {
    @ApiModelProperty(value = "Informacao do nome do usuário", example = "Arlei", position = 1)
    @NotBlank(message="Username e obrigatório")
    private String username;
    @ApiModelProperty(value = "Informacao da senha do usuário", example = "senha", position = 1)
    @NotBlank(message="Senha e obrigatória")
    private String senha;

    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setUsername(this.username);
        usuario.setSenha(this.senha);
        return usuario;
    }

    public void ToMapperEntity(Usuario entity) {

        entity.setUsername(this.username);
        entity.setSenha(this.senha);

    }
}
