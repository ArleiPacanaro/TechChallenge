package com.fiap.techchallenge.energia.dominio.eletrodomestico.entitie;

import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "idendereco")
    private Endereco endereco;

}
