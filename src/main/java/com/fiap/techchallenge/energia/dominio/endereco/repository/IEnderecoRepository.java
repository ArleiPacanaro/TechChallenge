package com.fiap.techchallenge.energia.dominio.endereco.repository;

import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEnderecoRepository extends JpaRepository<Endereco,Long> {
    Optional<Endereco> findByRuaOrBairroOrMunicipio(String nomeRua, String nomeBairro, String nomeMunicipio);
}
