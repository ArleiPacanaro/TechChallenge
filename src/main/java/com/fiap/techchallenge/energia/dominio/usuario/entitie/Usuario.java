package com.fiap.techchallenge.energia.dominio.usuario.entitie;

import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import com.fiap.techchallenge.energia.dominio.pessoa.entitie.Pessoa;
import com.fiap.techchallenge.energia.dominio.usuario.dto.response.UsuarioDTO;
import com.fiap.techchallenge.energia.dominio.usuario.dto.response.UsuarioPessoaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String senha;
    @OneToMany(mappedBy = "usuario")
    private List<Pessoa> pessoa;
    @OneToMany(mappedBy = "usuario")
    private List<Endereco> endereco;

    public UsuarioDTO ToUsuarioDTO() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(this.id);
        usuarioDTO.setUsername(this.username);
        usuarioDTO.setSenha(this.senha);

        return usuarioDTO;
    }

    public UsuarioPessoaDTO ToUsuarioPessoaDTO(){
        return new UsuarioPessoaDTO(this);
    }
}
