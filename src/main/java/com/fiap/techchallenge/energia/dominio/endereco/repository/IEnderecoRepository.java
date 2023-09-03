package com.fiap.techchallenge.energia.dominio.endereco.repository;

import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IEnderecoRepository extends JpaRepository<Endereco,Long> {
    List<Endereco> findByRuaOrBairroOrMunicipio(String nomeRua, String nomeBairro, String nomeMunicipio);
}
