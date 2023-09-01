package com.fiap.techchallenge.energia.dominio.endereco.repository;

import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnderecoRepository extends JpaRepository<Endereco,Long> {
}
