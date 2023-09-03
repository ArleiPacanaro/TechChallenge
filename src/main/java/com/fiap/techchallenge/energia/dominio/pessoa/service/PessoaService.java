package com.fiap.techchallenge.energia.dominio.pessoa.service;

import com.fiap.techchallenge.energia.dominio.pessoa.dto.PessoaDTO;
import com.fiap.techchallenge.energia.dominio.pessoa.entitie.Pessoa;
import com.fiap.techchallenge.energia.dominio.pessoa.repository.IPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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
    public ResponseEntity<List<PessoaDTO>> pesquisarPessoa (String nome, String parentesco, String sexo){

        Specification<Pessoa> spec = (root, query, criteriaBuilder) -> {
            // create a list of predicates
            List<Predicate> predicates = new ArrayList<>();

            // add a predicate for each search parameter
            if (nome != null) {
                predicates.add(criteriaBuilder.like(root.get("nome"), nome));
            }
            if (parentesco != null) {
                predicates.add(criteriaBuilder.equal(root.get("modelo"), parentesco));
            }
            if (sexo != null) {
                predicates.add(criteriaBuilder.equal(root.get("potencia"), sexo));
            }

            // combine the predicates into a single query
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        List<Pessoa> listaRetorno =  pessoaRepository.findAll(spec);
        if (!listaRetorno.isEmpty()) {
            return ResponseEntity.ok(listaRetorno.stream().map(PessoaDTO::new).collect(Collectors.toList()));
        }
        return ResponseEntity.noContent().build();
    }

}
