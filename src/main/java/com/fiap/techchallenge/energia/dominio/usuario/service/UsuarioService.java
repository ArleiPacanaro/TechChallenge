package com.fiap.techchallenge.energia.dominio.usuario.service;

import com.fiap.techchallenge.energia.dominio.usuario.dto.request.UsuarioRequestDTO;
import com.fiap.techchallenge.energia.dominio.usuario.dto.response.UsuarioDTO;
import com.fiap.techchallenge.energia.dominio.usuario.dto.response.UsuarioPessoaDTO;
import com.fiap.techchallenge.energia.dominio.usuario.entitie.Usuario;
import com.fiap.techchallenge.energia.dominio.usuario.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class UsuarioService {
    @Autowired
     IUsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioDTO save(UsuarioRequestDTO dto) {
        var entity = dto.toEntity();
        var enderecoSaved = usuarioRepository.save(entity);
        return enderecoSaved.ToUsuarioDTO();
    }


    @Transactional(readOnly = true)
    public Page<UsuarioPessoaDTO> findAll(PageRequest pageRequest) {
        var usuarios = usuarioRepository.findAll(pageRequest);
       return  usuarios.map(Usuario::ToUsuarioPessoaDTO);

    }


    @Transactional
    public UsuarioDTO update(Long id, UsuarioRequestDTO dto) {
        try {
            Usuario entity = usuarioRepository.getReferenceById(id);
            dto.ToMapperEntity(entity);

            var enderecoSaved = usuarioRepository.save(entity);
            return enderecoSaved.ToUsuarioDTO();

        }  catch (EntityNotFoundException e) {
            throw new RuntimeException("Usuario não encontrado, id: " + id);
        }
    }

    @Transactional
    public void delete(Long id)  {
        try {
            usuarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Violação de integridade dos dados");
        }
    }


    @Transactional(readOnly = true)
    public UsuarioPessoaDTO findById(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Endereço não encontrado")
        );

        return usuario.ToUsuarioPessoaDTO();
    }

}
