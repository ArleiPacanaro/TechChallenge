package com.fiap.techchallenge.energia.dominio.eletrodomestico.entitie;

import com.fiap.techchallenge.energia.dominio.eletrodomestico.dto.response.EletrodomesticoDTO;
import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_eletrodomestico")
public class Eletrodomestico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "potencia")
    private Integer potencia;

    @Column(name = "serialnumber")
    private String serialNumber;

    @Column(name = "idendereco")
    private Long idendereco;

    @ManyToOne
    @JoinColumn(name = "idendereco", insertable=false, updatable=false)
    private Endereco endereco;

    public EletrodomesticoDTO ToEletrodomesticoDTO(){
        return new EletrodomesticoDTO(this);
    }

}
