package com.fiap.techchallenge.energia.dominio.pessoa.service;

import com.fiap.techchallenge.energia.dominio.pessoa.dto.PessoaDTO;
import com.fiap.techchallenge.energia.dominio.pessoa.entitie.Pessoa;
import com.fiap.techchallenge.energia.dominio.pessoa.repository.IPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public PessoaDTO save(PessoaDTO dto) {
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
    public PessoaDTO update(Long id, PessoaDTO dto) {
        try {

            // tem que fazer desta forma para entender que a entendidade existe no banco de dados...
            Pessoa entity = pessoaRepository.getReferenceById(id);
            dto.ToMapperEntity(entity);

            var pessoaSaved = pessoaRepository.save(entity);
            return pessoaSaved.ToPessoaDTO();

        }  catch (EntityNotFoundException e) {
            throw new RuntimeException("Endereço não encontrado, id: " + id);
        }
    }

    @Transactional
    public void delete(Long id)  {
        try {
            pessoaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Violação de integridade dos dados");
        }
    }

    @Transactional(readOnly = true)
    public PessoaDTO findById(Long id) {
        var pessoa = pessoaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Endereço não encontrado")
        );
        return pessoa.ToPessoaDTO();
    }
    @Transactional(readOnly = true)
    public List<PessoaDTO> findByParam(String nomeNome, String nomeParentesco, String nomeSexo) {
        var pessoa = pessoaRepository.findByNomeOrParentescoOrSexo(nomeNome, nomeParentesco, nomeSexo);
        return pessoa.stream().map(PessoaDTO::new).collect(Collectors.toList());
    }

}
