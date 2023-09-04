package com.fiap.techchallenge.energia.dominio.usuario.dto.response;

import com.fiap.techchallenge.energia.dominio.endereco.dto.response.EnderecoDTO;
import com.fiap.techchallenge.energia.dominio.pessoa.dto.response.PessoaDTO;
import com.fiap.techchallenge.energia.dominio.usuario.entitie.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPessoaDTO {

    private Long id;
    private String username;
    private String senha;
    private List<PessoaDTO> pessoa = new ArrayList<>();
    private List<EnderecoDTO> endereco = new ArrayList<>();

    public UsuarioPessoaDTO(Usuario usuario)
    {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.senha = usuario.getSenha();
        usuario.getPessoa().forEach( pessoa -> this.pessoa.add(new PessoaDTO(pessoa)));
        usuario.getEndereco().forEach( endereco -> this.endereco.add(new EnderecoDTO(endereco)));
    }

}
