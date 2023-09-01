package com.fiap.techchallenge.energia.dominio.usuario.repository;

import com.fiap.techchallenge.energia.dominio.usuario.entitie.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {
}
