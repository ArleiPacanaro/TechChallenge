package com.fiap.techchallenge.energia.dominio.eletrodomestico.repository;

import com.fiap.techchallenge.energia.dominio.eletrodomestico.entitie.Eletrodomestico;
import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EletrodomesticoRepository extends JpaRepository<Eletrodomestico, Long> {

    Optional<Eletrodomestico> findById(Long id);

    List<Eletrodomestico> findByNomeOrModeloOrPotencia(String nome, String modelo, Integer potencia);

}
