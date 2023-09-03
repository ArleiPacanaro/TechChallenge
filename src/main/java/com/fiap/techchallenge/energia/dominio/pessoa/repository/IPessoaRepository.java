package com.fiap.techchallenge.energia.dominio.pessoa.repository;

import com.fiap.techchallenge.energia.dominio.pessoa.entitie.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa,Long> {
    Optional<Pessoa> findByNomeOrParentescoOrSexo(String nomeNome, String nomeParentesco, String nomeSexo);
}
