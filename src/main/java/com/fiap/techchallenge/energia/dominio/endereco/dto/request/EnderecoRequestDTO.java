package com.fiap.techchallenge.energia.dominio.endereco.dto.request;

import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@ApiModel(description = "Representa um objeto de  Endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDTO {
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

    @ApiModelProperty(value = "Complemento do endereco", example = "Apartamento 101", position = 1)
    private String complemento;

    @ApiModelProperty(value = "Código  com o ID do usuario", example = "1", position = 1)
    @NotNull(message = "id do usuario deve ser preenchido")
    private Long idusuario;

    public Endereco toEntity() {
        Endereco endereco = new Endereco();

        endereco.setPais(this.pais);
        endereco.setEstado(this.estado);
        endereco.setMunicipio(this.municipio);
        endereco.setBairro(this.bairro);
        endereco.setRua(this.rua);
        endereco.setCep(this.cep);
        endereco.setComplemento(this.complemento);
        endereco.setIdusuario(this.idusuario);

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
        endereco.setIdusuario(this.idusuario);
    }
}
