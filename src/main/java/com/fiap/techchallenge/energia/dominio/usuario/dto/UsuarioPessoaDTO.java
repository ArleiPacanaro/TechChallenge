package com.fiap.techchallenge.energia.dominio.usuario.dto;

import com.fiap.techchallenge.energia.dominio.pessoa.dto.PessoaDTO;
import com.fiap.techchallenge.energia.dominio.pessoa.entitie.Pessoa;
import com.fiap.techchallenge.energia.dominio.usuario.entitie.Usuario;
import io.swagger.annotations.ApiModelProperty;
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

    public UsuarioPessoaDTO(Usuario usuario)
    {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.senha = usuario.getSenha();
        usuario.getPessoa().forEach( x ->

        this.pessoa.add( new PessoaDTO( x
//                x.getId(),
//                x.getCpf(),
//                x.getNome(),
//                x.getTelefone(),
//                x.getEmail(),
//                x.getSenha(),
//                x.getDatanascimento(),
//                x.getSexo(),
//                x.getParentesco(),
//                x.getId()
                )));
 }

}
