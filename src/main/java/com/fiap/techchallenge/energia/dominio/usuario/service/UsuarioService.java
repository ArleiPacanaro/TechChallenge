package com.fiap.techchallenge.energia.dominio.usuario.service;

import com.fiap.techchallenge.energia.dominio.usuario.dto.UsuarioDTO;
import com.fiap.techchallenge.energia.dominio.usuario.dto.UsuarioPessoaDTO;
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

    private final IUsuarioRepository repo;

    @Autowired
    public UsuarioService(IUsuarioRepository repo) {
        this.repo = repo;
    }


    @Transactional
    public UsuarioDTO save(UsuarioDTO dto) {
        var entity = dto.toEntity();
        var enderecoSaved = repo.save(entity);
        return enderecoSaved.ToUsuarioDTO();
    }


    @Transactional(readOnly = true)
    public Page<UsuarioPessoaDTO> findAll(PageRequest pageRequest) {
        var usuarios = repo.findAll(pageRequest);
       return  usuarios.map(Usuario::ToUsuarioPessoaDTO);

    }


    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        try {

            // tem que fazer desta forma para entender que a entendidade existe no banco de dados...
            Usuario entity = repo.getReferenceById(id);
            dto.ToMapperEntity(entity);

            var enderecoSaved = repo.save(entity);
            return enderecoSaved.ToUsuarioDTO();

        }  catch (EntityNotFoundException e) {
            throw new RuntimeException("Endereço não encontrado, id: " + id);
        }
    }

    @Transactional
    public void delete(Long id)  {
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Violação de integridade dos dados");
        }
    }


    @Transactional(readOnly = true)
    public UsuarioPessoaDTO findById(Long id) {
        var usuario = repo.findById(id).orElseThrow(
                () -> new RuntimeException("Endereço não encontrado")
        );

        return usuario.ToUsuarioPessoaDTO();
    }

}
