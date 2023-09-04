package com.fiap.techchallenge.energia.dominio.pessoa.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.techchallenge.energia.dominio.pessoa.entitie.Pessoa;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;

    @ApiModelProperty(value = "CPF válido do usuario", example = "07506450089", position = 1)
    @CPF(message = "CPF deve ser valido")
    @NotBlank(message = "CPF deve ser preenchido")
    private String cpf;

    @NotBlank(message = "Nome deve ser preenchido")
    @ApiModelProperty(value = "Informacao do Nome da pessoa", example = "Zezinho", position = 1)
    private String nome;

    @ApiModelProperty(value = "Informacao do telefone da pessoa", example = "11 3258-5303", position = 1)
    @NotBlank(message = "Telefone deve ser preenchido")
    private String telefone;

    @ApiModelProperty(value = "Informacao do email da pessoa", example = "xs@hotmail.com", position = 1)
    @NotBlank(message = "email deve ser preenchido")
    private String email;

    @ApiModelProperty(value = "Informacao da senha da pessoa", example = "123", position = 1)
    @NotBlank(message = "senha deve ser preenchido")
    private String senha;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Past(message = "Data de nascimento deve ser maior que a Data Atual")
    @ApiModelProperty(value = "Informacao da data de nascimento da pessoa", example = "01/01/2000", position = 1)
    private LocalDate datanascimento;

    @ApiModelProperty(value = "Informacao do Sexo da pessoa", example = "MASCULINO", position = 1)
    @NotBlank(message = "sexo deve ser preenchido")
    private String sexo;

    @ApiModelProperty(value = "Informacao do parentesco da pessoa com o usuario", example = "FILHOS", position = 1)
    @NotBlank(message = "parentesco deve ser preenchido")
    private String parentesco;

    @ApiModelProperty(value = "Código  com o ID do usuario", example = "2", position = 1)
    @NotNull(message = "id do usuario deve ser preenchido")
    private Long idusuario;

    public PessoaDTO(Pessoa pessoa) {
                this.id = pessoa.getId();
                this.cpf = pessoa.getCpf();
                this.nome = pessoa.getNome();
                this.telefone = pessoa.getTelefone();
                this.email = pessoa.getEmail();
                this.senha = pessoa.getSenha();
                this.datanascimento = pessoa.getDatanascimento();
                this.sexo = pessoa.getSexo();
                this.parentesco = pessoa.getParentesco();
                this.idusuario = pessoa.getIdusuario();
    }

}
