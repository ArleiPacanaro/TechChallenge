package com.fiap.techchallenge.energia.dominio.endereco.dto;

import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private Long id;
    private String pais;
    private String estado;
    private String municipio;
    private String bairro;
    private String rua;
    private String cep;
    private String complemento;

    public EnderecoDTO(Endereco endereco) {
        this(
                endereco.getId(),
                endereco.getPais(),
                endereco.getEstado(),
                endereco.getMunicipio(),
                endereco.getBairro(),
                endereco.getRua(),
                endereco.getCep(),
                endereco.getComplemento()

        );

    }



}
