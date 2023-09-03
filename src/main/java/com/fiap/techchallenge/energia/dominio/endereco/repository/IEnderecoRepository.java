package com.fiap.techchallenge.energia.dominio.endereco.repository;

import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IEnderecoRepository extends JpaRepository<Endereco,Long>, JpaSpecificationExecutor<Endereco> {
}
