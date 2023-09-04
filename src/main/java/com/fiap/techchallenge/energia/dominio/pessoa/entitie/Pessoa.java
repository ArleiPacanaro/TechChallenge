package com.fiap.techchallenge.energia.dominio.pessoa.entitie;

import com.fiap.techchallenge.energia.dominio.pessoa.dto.response.PessoaDTO;
import com.fiap.techchallenge.energia.dominio.usuario.entitie.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_pessoa")
public class Pessoa  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private LocalDate datanascimento;
    private String sexo;
    private String parentesco;
    private Long idusuario;

    @ManyToOne
    @JoinColumn(name = "idusuario" ,insertable=false, updatable=false)
    private Usuario usuario;

    public PessoaDTO ToPessoaDTO() {

        PessoaDTO pessoaDTO = new PessoaDTO();

        pessoaDTO.setId(this.id);
        pessoaDTO.setCpf(this.cpf);
        pessoaDTO.setNome(this.nome);
        pessoaDTO.setTelefone(this.telefone);
        pessoaDTO.setEmail(this.email);
        pessoaDTO.setSenha(this.senha);
        pessoaDTO.setDatanascimento(this.datanascimento);
        pessoaDTO.setSexo(this.sexo);
        pessoaDTO.setParentesco(this.parentesco);
        pessoaDTO.setIdusuario(idusuario);

        return pessoaDTO;
    }
}
