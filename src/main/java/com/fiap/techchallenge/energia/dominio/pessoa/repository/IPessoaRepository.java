package com.fiap.techchallenge.energia.dominio.pessoa.repository;

import com.fiap.techchallenge.energia.dominio.pessoa.entitie.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa,Long>, JpaSpecificationExecutor<Pessoa> {
}
