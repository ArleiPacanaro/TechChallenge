package com.fiap.techchallenge.energia.dominio.eletrodomestico.service;


import com.fiap.techchallenge.energia.config.ValidatorBean;
import com.fiap.techchallenge.energia.dominio.eletrodomestico.dto.request.EletrodomesticoRequest;
import com.fiap.techchallenge.energia.dominio.eletrodomestico.dto.response.EletrodomesticoResponseDto;
import com.fiap.techchallenge.energia.dominio.eletrodomestico.entitie.Eletrodomestico;
import com.fiap.techchallenge.energia.dominio.eletrodomestico.repository.EletrodomesticoRepository;
import com.fiap.techchallenge.energia.dominio.endereco.repository.IEnderecoRepository;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Predicate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EletrodomesticoService {

    private static final String BAD_REQUEST_VAZIO = "As informacoes do resquest sao nulas ou o cadastro ja existe.";

    @Autowired
    EletrodomesticoRepository eletrodomesticoRepository;

    @Autowired
    private ValidatorBean validator;

    @Autowired
    private IEnderecoRepository enderecoRepository;

    public ResponseEntity cadastrarEletrodomestico (EletrodomesticoRequest eletrodomesticoRequest){

        final var validar = validator.validar(eletrodomesticoRequest);
        final var enderecoEncontrado = enderecoRepository.findById(eletrodomesticoRequest.getIdEndereco());
        if(!enderecoEncontrado.isEmpty() && validar.isEmpty()) {
                Eletrodomestico eletrodomestico = new Eletrodomestico();
                eletrodomestico.setNome(eletrodomesticoRequest.getNome());
                eletrodomestico.setModelo(eletrodomesticoRequest.getModelo());
                eletrodomestico.setPotencia(eletrodomesticoRequest.getPotencia());
                eletrodomestico.setSerialNumber(eletrodomesticoRequest.getSerialNumber());
                eletrodomestico.setEndereco(enderecoEncontrado.get());
                try{
                    Eletrodomestico eletrodomesticoSalvo = eletrodomesticoRepository.save(eletrodomestico);
                    return ResponseEntity.status(HttpStatus.CREATED).body(new EletrodomesticoResponseDto(eletrodomesticoSalvo));
                } catch (Exception ex){
                    if(ex.toString().contains("tb_eletrodomestico_serialnumber_key")){
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro! SerialNumber já existe");
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.toString());
                    }
                }
            } else {
                if (enderecoEncontrado.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço informado não existe!");
                } else {
                    return ResponseEntity.internalServerError().body("");
                }
            }
        }
    public ResponseEntity<EletrodomesticoResponseDto> consultarEletrodomestico(Long id) {

        final var eletrodomesticoEncontrado = eletrodomesticoRepository.findById(id);

        if (!eletrodomesticoEncontrado.isEmpty()) {
            return ResponseEntity.ok(new EletrodomesticoResponseDto(eletrodomesticoEncontrado.get()));
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<EletrodomesticoResponseDto>> pesquisarEletrodomestico (String nome, String modelo, String potencia, String serialNumber){

        Specification<Eletrodomestico> spec = (root, query, criteriaBuilder) -> {
            // create a list of predicates
            List<Predicate> predicates = new ArrayList<>();

            // add a predicate for each search parameter
            if (nome != null) {
                predicates.add(criteriaBuilder.like(root.get("nome"), nome));
            }
            if (modelo != null) {
                predicates.add(criteriaBuilder.equal(root.get("modelo"), modelo));
            }
            if (potencia != null) {
                predicates.add(criteriaBuilder.equal(root.get("potencia"), potencia));
            }
            if (serialNumber != null) {
                predicates.add(criteriaBuilder.equal(root.get("serialNumber"), serialNumber));
            }

            // combine the predicates into a single query
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        List<Eletrodomestico> listaRetorno =  eletrodomesticoRepository.findAll(spec);
        if (!listaRetorno.isEmpty()) {
            return ResponseEntity.ok(listaRetorno.stream().map(EletrodomesticoResponseDto::new).collect(Collectors.toList()));
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity atualizarEletrodomestico(EletrodomesticoRequest eletrodomesticoRequest, Long id) {

        if (!ObjectUtils.isEmpty(id)) {

            final var eletrodomesticoEncontrado = eletrodomesticoRepository.findById(id);
            final var validar = validator.validar(eletrodomesticoRequest);

            if (!eletrodomesticoEncontrado.isEmpty() && validar.isEmpty()) {

                eletrodomesticoEncontrado.get().setNome(eletrodomesticoRequest.getNome());
                eletrodomesticoEncontrado.get().setModelo(eletrodomesticoRequest.getModelo());
                eletrodomesticoEncontrado.get().setPotencia(eletrodomesticoRequest.getPotencia());
                eletrodomesticoEncontrado.get().setSerialNumber(eletrodomesticoRequest.getSerialNumber());

                if(!eletrodomesticoEncontrado.get().getEndereco().getId().equals(eletrodomesticoRequest.getIdEndereco())){
                    final var enderecoEncontrado = enderecoRepository.findById(eletrodomesticoRequest.getIdEndereco());
                    if(!enderecoEncontrado.isEmpty()){
                        eletrodomesticoEncontrado.get().setEndereco(enderecoEncontrado.get());
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço informado não encontrado");
                    }
                }
                try{
                    Eletrodomestico eletrodomesticoAtualizado = eletrodomesticoRepository.save(eletrodomesticoEncontrado.get());
                    return ResponseEntity.ok(new EletrodomesticoResponseDto(eletrodomesticoAtualizado));
                } catch (Exception ex){
                    if(ex.toString().contains("tb_eletrodomestico_serialnumber_key")){
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro! SerialNumber já existe");
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.toString());
                    }
                }
            }
            if(!validar.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validar);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Eletrodomestico não encontrado");
            }

        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<EletrodomesticoResponseDto> deletarEletrodomestico(Long id) {

        if (!ObjectUtils.isEmpty(id)) {

            final var eletrodomesticoEncontrado = eletrodomesticoRepository.findById(id);

            if (!eletrodomesticoEncontrado.isEmpty()) {

                eletrodomesticoRepository.deleteById(id);

                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity retornarConsumo(Long id, Long hora) {
        if (!ObjectUtils.isEmpty(id)) {

            final var eletrodomesticoEncontrado = eletrodomesticoRepository.findById(id);

            if (!eletrodomesticoEncontrado.isEmpty()) {
                Double potencia = Double.valueOf(eletrodomesticoEncontrado.get().getPotencia());
                Double consumo = (potencia / 1000) * (hora/60);
                return ResponseEntity.ok().body("O eletrodomestico " + eletrodomesticoEncontrado.get().getNome() + " consumiu " + consumo + "kwh");
            }
        }
        return ResponseEntity.badRequest().build();
    }
}