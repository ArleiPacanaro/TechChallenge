package com.fiap.techchallenge.energia.dominio.eletrodomestico.repository;

import com.fiap.techchallenge.energia.dominio.eletrodomestico.entitie.Eletrodomestico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EletrodomesticoRepository extends JpaRepository<Eletrodomestico, Long>, JpaSpecificationExecutor<Eletrodomestico> {

    Optional<Eletrodomestico> findById(Long id);

}
