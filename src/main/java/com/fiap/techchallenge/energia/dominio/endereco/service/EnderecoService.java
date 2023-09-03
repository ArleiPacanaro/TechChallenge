package com.fiap.techchallenge.energia.dominio.endereco.service;

import com.fiap.techchallenge.energia.config.ValidatorBean;
import com.fiap.techchallenge.energia.dominio.endereco.dto.EnderecoDTO;
import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import com.fiap.techchallenge.energia.dominio.endereco.repository.IEnderecoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {
    private static final String BAD_REQUEST_VAZIO = "As informacoes do resquest sao nulas ou o cadastro ja existe.";

    private static Logger logger = LoggerFactory.getLogger(EnderecoService.class);

//    @Autowired
//    EletrodomesticoRepository eletrodomesticoRepository;
    @Autowired
    IEnderecoRepository enderecoRepository;

    @Autowired
    private ValidatorBean validator;

//    @Autowired
//    private IEnderecoRepository enderecoRepository;
    public ResponseEntity<List<EnderecoDTO>> pesquisarEndereco (String rua, String bairro, String cidade){

        Specification<Endereco> spec = (root, query, criteriaBuilder) -> {
            // create a list of predicates
            List<Predicate> predicates = new ArrayList<>();

            // add a predicate for each search parameter
            if (rua != null) {
                predicates.add(criteriaBuilder.like(root.get("rua"), rua));
            }
            if (bairro != null) {
                predicates.add(criteriaBuilder.equal(root.get("bairro"), bairro));
            }
            if (cidade != null) {
                predicates.add(criteriaBuilder.equal(root.get("cidade"), cidade));
            }

            // combine the predicates into a single query
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        List<Endereco> listaRetorno =  enderecoRepository.findAll(spec);
        if (!listaRetorno.isEmpty()) {
            return ResponseEntity.ok(listaRetorno.stream().map(EnderecoDTO::new).collect(Collectors.toList()));
        }
        return ResponseEntity.noContent().build();

    }
}
