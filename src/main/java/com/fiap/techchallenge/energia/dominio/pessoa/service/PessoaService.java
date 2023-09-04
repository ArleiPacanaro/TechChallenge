package com.fiap.techchallenge.energia.dominio.pessoa.service;

import com.fiap.techchallenge.energia.dominio.pessoa.dto.request.PessoaRequestDTO;
import com.fiap.techchallenge.energia.dominio.pessoa.dto.response.PessoaDTO;
import com.fiap.techchallenge.energia.dominio.pessoa.entitie.Pessoa;
import com.fiap.techchallenge.energia.dominio.pessoa.repository.IPessoaRepository;
import com.fiap.techchallenge.energia.exception.service.DatabaseException;
import com.fiap.techchallenge.energia.exception.service.ServiceNotFoundedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    private final IPessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(IPessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public PessoaDTO save(PessoaRequestDTO dto) {
        var entity = dto.toEntity();
        var pessoaSaved = pessoaRepository.save(entity);
        return pessoaSaved.ToPessoaDTO();
    }

    @Transactional(readOnly = true)
    public Page<PessoaDTO> findAll(PageRequest pageRequest) {
        var pessoas = pessoaRepository.findAll(pageRequest);
        return  pessoas.map(Pessoa::ToPessoaDTO);
    }

    @Transactional
    public PessoaDTO update(Long id, PessoaRequestDTO dto) {
        try {
            Pessoa entity = pessoaRepository.getReferenceById(id);
            dto.ToMapperEntity(entity);

            var pessoaSaved = pessoaRepository.save(entity);
            return pessoaSaved.ToPessoaDTO();

        }  catch (EntityNotFoundException e) {
            throw new ServiceNotFoundedException("Pessoa não encontrada, id: " + id);
        }
    }

    @Transactional
    public void delete(Long id)  {
        try {
            pessoaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade dos dados");
        }
    }

//    @Transactional(readOnly = true)
//    public PessoaDTO findById(Long id) {
//        var pessoa = pessoaRepository.findById(id).orElseThrow(
//                () -> new ServiceNotFoundedException("Pessoa não encontrada")
//        );
//        return pessoa.ToPessoaDTO();
//    }

    @Transactional(readOnly = true)
    public ResponseEntity<PessoaDTO> findById(Long id) {
        var pessoa = pessoaRepository.findById(id);
        if(pessoa.isPresent()) {
            return ResponseEntity.ok(pessoa.get().ToPessoaDTO());
        }
        return ResponseEntity.notFound().build();
    }

//    @Transactional(readOnly = true)
//    public List<PessoaDTO> findByParam(String nomeNome, String nomeParentesco, String nomeSexo) {
//        try{
//            var pessoa = pessoaRepository.findByNomeOrParentescoOrSexo(nomeNome, nomeParentesco, nomeSexo);
//            return pessoa.stream().map(PessoaDTO::new).collect(Collectors.toList());
//        } catch (EntityNotFoundException e) {
//            throw new ServiceNotFoundedException("Pessoa não encontrada");
//        }
//    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<PessoaDTO>> findByParam(String nomeNome, String nomeParentesco, String nomeSexo) {
        var pessoa = pessoaRepository.findByNomeOrParentescoOrSexo(nomeNome, nomeParentesco, nomeSexo);
        if(!pessoa.isEmpty()) {
            return ResponseEntity.ok(pessoa.stream().map(PessoaDTO::new).collect(Collectors.toList()));
        }
        return ResponseEntity.notFound().build();
    }

}
