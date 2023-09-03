package com.fiap.techchallenge.energia.dominio.endereco.entitie;

import com.fiap.techchallenge.energia.dominio.endereco.dto.EnderecoDTO;
import com.fiap.techchallenge.energia.dominio.pessoa.entitie.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pais;
    private String estado;
    private String municipio;
    private String bairro;
    private String cep;
    private String complemento;
    private String rua;

    @ManyToMany
    @JoinTable(
            name = "tb_pessoa_endereco",
            joinColumns = @JoinColumn(name = "idEndereco"),
            inverseJoinColumns = @JoinColumn(name = "idPessoa")
    )
    Set<Pessoa> pessoas = new HashSet<>();

//    @OneToMany(mappedBy = "endereco")
//    private Set<Eletrodomestico> eletrodomesticos = new HashSet<>();

    public EnderecoDTO ToEnderecoDTO() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setId(this.id);
        enderecoDTO.setPais(this.pais);
        enderecoDTO.setEstado(this.estado);
        enderecoDTO.setMunicipio(this.municipio);
        enderecoDTO.setBairro(this.bairro);
        enderecoDTO.setCep(this.cep);
        enderecoDTO.setComplemento(this.complemento);
        enderecoDTO.setRua(this.rua);

        return enderecoDTO;
    }
}
