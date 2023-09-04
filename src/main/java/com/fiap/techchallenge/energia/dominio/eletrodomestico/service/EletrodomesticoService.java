package com.fiap.techchallenge.energia.dominio.eletrodomestico.service;

import com.fiap.techchallenge.energia.dominio.eletrodomestico.dto.request.EletrodomesticoRequestDTO;
import com.fiap.techchallenge.energia.dominio.eletrodomestico.dto.response.EletrodomesticoDTO;
import com.fiap.techchallenge.energia.dominio.eletrodomestico.entitie.Eletrodomestico;
import com.fiap.techchallenge.energia.dominio.eletrodomestico.repository.EletrodomesticoRepository;
import com.fiap.techchallenge.energia.exception.service.DatabaseException;
import com.fiap.techchallenge.energia.exception.service.ServiceNotFoundedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EletrodomesticoService {

    @Autowired
    EletrodomesticoRepository eletrodomesticoRepository;

    @Transactional
    public EletrodomesticoDTO save(EletrodomesticoRequestDTO eletrodomesticoDTO) {
        var eletrodomesticoEntity = eletrodomesticoDTO.toEntity();
        var eletrodomesticoSaved = eletrodomesticoRepository.save(eletrodomesticoEntity);
        return eletrodomesticoSaved.ToEletrodomesticoDTO();
    }

    @Transactional
    public Page<EletrodomesticoDTO> findAll(PageRequest pageRequest) {
        var eletrodomesticos = eletrodomesticoRepository.findAll(pageRequest);
        return eletrodomesticos.map(Eletrodomestico::ToEletrodomesticoDTO);
    }

//    @Transactional(readOnly = true)
//    public EletrodomesticoDTO findById(Long id) {
//        var eletrodomesticos = eletrodomesticoRepository.findById(id).orElseThrow(
//                () -> new ServiceNotFoundedException("Eletrodomestico não encontrado")
//        );
//        return eletrodomesticos.ToEletrodomesticoDTO();
//    }

    @Transactional(readOnly = true)
    public ResponseEntity<EletrodomesticoDTO> findById(Long id) {
        var eletrodomesticos = eletrodomesticoRepository.findById(id);
        if(eletrodomesticos.isPresent()) {
            return ResponseEntity.ok(eletrodomesticos.get().ToEletrodomesticoDTO());
        }
        return  ResponseEntity.notFound().build();
    }

//    @Transactional(readOnly = true)
//    public List<EletrodomesticoDTO> findByParam(String nome, String modelo, Integer potencia) {
//        try{
//            var eletrodomestico = eletrodomesticoRepository.findByNomeOrModeloOrPotencia(nome, modelo, potencia);
//            return eletrodomestico.stream().map(EletrodomesticoDTO::new).collect(Collectors.toList());
//        } catch (EntityNotFoundException e) {
//            throw new ServiceNotFoundedException("Eletrodomestico não encontrada");
//        }
//    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<EletrodomesticoDTO>> findByParam(String nome, String modelo, Integer potencia) {
        var eletrodomestico = eletrodomesticoRepository.findByNomeOrModeloOrPotencia(nome, modelo, potencia);
        if(!eletrodomestico.isEmpty()) {
            return ResponseEntity.ok(eletrodomestico.stream().map(EletrodomesticoDTO::new).collect(Collectors.toList()));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public EletrodomesticoDTO update(Long id, EletrodomesticoRequestDTO eletrodomesticoDTO) {
        try {
            Eletrodomestico eletrodomesticoEntity = eletrodomesticoRepository.getReferenceById(id);
            eletrodomesticoDTO.ToMapperEntity(eletrodomesticoEntity);

            var eletrodomesticoSaved = eletrodomesticoRepository.save(eletrodomesticoEntity);
            return eletrodomesticoSaved.ToEletrodomesticoDTO();
        } catch (EntityNotFoundException e) {
            throw new ServiceNotFoundedException("Eletrodomestico não encontrado, id: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            eletrodomesticoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade dos dados");
        }
    }

    @Transactional
    public ResponseEntity retornarConsumo(Long id, Long minutos) {
        if (!ObjectUtils.isEmpty(id)) {

            final var eletrodomesticoEncontrado = eletrodomesticoRepository.findById(id);

            if (!eletrodomesticoEncontrado.isEmpty()) {
                Double potencia = Double.valueOf(eletrodomesticoEncontrado.get().getPotencia());
                Double consumo = (potencia / 1000) * (minutos / 60);
                return ResponseEntity.ok().body("O eletrodomestico " + eletrodomesticoEncontrado.get().getNome() + " consumiu " + consumo + " kwh");
            }
        }
        return ResponseEntity.badRequest().build();
    }

}