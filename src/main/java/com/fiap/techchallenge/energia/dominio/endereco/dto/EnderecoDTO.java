package com.fiap.techchallenge.energia.dominio.endereco.dto;

import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import com.fiap.techchallenge.energia.dominio.pessoa.dto.PessoaDTO;
import com.fiap.techchallenge.energia.dominio.pessoa.entitie.Pessoa;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    @ApiModelProperty(value = "ID do endereco", example = "1", position = 1)
    private Long id;

    @ApiModelProperty(value = "Nome do país", example = "BR", position = 1)
    @NotBlank(message = "O nome do País deve ser preenchido")
    private String pais;

    @ApiModelProperty(value = "Nome do estado", example = "RJ", position = 1)
    @NotBlank(message = "O nome do Estado deve ser preenchido")
    private String estado;

    @ApiModelProperty(value = "Nome do município", example = "Rio de Janeiro", position = 1)
    @NotBlank(message = "O nome do Município deve ser preenchido")
    private String municipio;

    @ApiModelProperty(value = "Nome do bairro", example = "Copacabana", position = 1)
    @NotBlank(message = "O nome do Bairro deve ser preenchido")
    private String bairro;

    @ApiModelProperty(value = "Nome da rua", example = "Nossa Sra de Copacabana", position = 1)
    @NotBlank(message = "O nome da Rua deve ser preenchida")
    private String rua;

    @ApiModelProperty(value = "CEP do endereco", example = "21000-000", position = 1)
    @Pattern(regexp = "^[0-9]{1,5}\\-[0-9]{1,3}$", message = "O número do CEP deve ter o formato 00000-000.")
    @NotBlank(message = "O número do CEP deve ser preenchido")
    private String cep;

    @ApiModelProperty(value = "Complemento do endereco", example = "AP 101 - Condominio Bosque das Rosas", position = 1)
    private String complemento;

    @ApiModelProperty(value = "Pessoas relacionados com o endereço", example = "2", position = 1)
    private Set<PessoaDTO> pessoaDTOSet = new HashSet<>();

//    @ApiModelProperty(value = "Eletrodomesticos relacionados com o endereço", example = "2", position = 1)
//    private Set<EletrodomesticoDTO> eletrodomesticoDTOSet = new HashSet<>();

    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.pais = endereco.getPais();
        this.estado = endereco.getEstado();
        this.municipio = endereco.getMunicipio();
        this.bairro = endereco.getBairro();
        this.rua = endereco.getRua();
        this.cep = endereco.getCep();
        this.complemento = endereco.getComplemento();
    }

    public EnderecoDTO(Endereco endereco, Set<Pessoa> pessoas) {
        this(endereco);
        pessoas.forEach(pessoa -> this.pessoaDTOSet.add(new PessoaDTO(pessoa)));
    }

//    public EnderecoDTO(Endereco endereco, Set<Pessoa> pessoas, Set<Eletrodomestico> eletrodomesticos) {
//       this(endereco);
//        pessoas.forEach(pessoa -> this.pessoaDTOSet.add(new PessoaDTO(pessoa)));
//        eletrodomesticos.forEach(eletrodomestico -> this.eletrodomesticoDTOSet.add(new EletrodomesticosDTO(eletrodomestico)));
//    }

    public Endereco toEntity() {
        Endereco endereco = new Endereco();

        endereco.setPais(this.pais);
        endereco.setEstado(this.estado);
        endereco.setMunicipio(this.municipio);
        endereco.setBairro(this.bairro);
        endereco.setRua(this.rua);
        endereco.setCep(this.cep);
        endereco.setComplemento(this.complemento);

        return endereco;
    }

    public void ToMapperEntity(Endereco endereco) {
        endereco.setPais(this.pais);
        endereco.setEstado(this.estado);
        endereco.setMunicipio(this.municipio);
        endereco.setBairro(this.bairro);
        endereco.setRua(this.rua);
        endereco.setCep(this.cep);
        endereco.setComplemento(this.complemento);
    }
}
