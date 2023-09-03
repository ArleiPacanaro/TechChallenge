package com.fiap.techchallenge.energia.dominio.usuario.repository;

import com.fiap.techchallenge.energia.dominio.usuario.entitie.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {
}
