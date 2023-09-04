package com.fiap.techchallenge.energia.dominio.endereco.service;

import com.fiap.techchallenge.energia.dominio.endereco.dto.request.EnderecoRequestDTO;
import com.fiap.techchallenge.energia.dominio.endereco.dto.response.EnderecoDTO;
import com.fiap.techchallenge.energia.dominio.endereco.dto.response.EnderecoEletrodomesticoDTO;
import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import com.fiap.techchallenge.energia.dominio.endereco.repository.IEnderecoRepository;
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
public class EnderecoService {

    private final IEnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(IEnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public EnderecoDTO save(EnderecoRequestDTO enderecoDTO) {
        var enderecoEntity = enderecoDTO.toEntity();
        var enderecoSaved = enderecoRepository.save(enderecoEntity);
        return enderecoSaved.ToEnderecoDTO();
    }

    @Transactional(readOnly = true)
    public Page<EnderecoEletrodomesticoDTO> findAll(PageRequest pageRequest) { //alterar pelo DTO a ser criado para retornar o endereco vinculado as pessoas e eletrodomesticos
        var enderecos = enderecoRepository.findAll(pageRequest);
        return  enderecos.map(Endereco::ToEnderecoEletrodomesticoDTO); //alterar pelo DTO a ser criado para retornar o endereco vinculado as pessoas e eletrodomesticos
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoRequestDTO enderecoDTO) {
        try {
            Endereco enderecoEntity = enderecoRepository.getReferenceById(id);
            enderecoDTO.ToMapperEntity(enderecoEntity);

            var enderecoSaved = enderecoRepository.save(enderecoEntity);
            return enderecoSaved.ToEnderecoDTO();
        }  catch (EntityNotFoundException e) {
            throw new ServiceNotFoundedException("Endereço não encontrado, id: " + id);
        }
    }

    @Transactional
    public void delete(Long id)  {
        try {
            enderecoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade dos dados");
        }
    }

//    @Transactional(readOnly = true)
//    public EnderecoEletrodomesticoDTO findById(Long id) {
//        var endereco = enderecoRepository.findById(id).orElseThrow(
//                () -> new ServiceNotFoundedException("Endereço não encontrado")
//        );
//        return endereco.ToEnderecoEletrodomesticoDTO();
//    }

    @Transactional(readOnly = true)
    public ResponseEntity<EnderecoEletrodomesticoDTO> findById(Long id) {
        var endereco = enderecoRepository.findById(id);
        if(endereco.isPresent()) {
            return ResponseEntity.ok(endereco.get().ToEnderecoEletrodomesticoDTO());
        }
        return ResponseEntity.notFound().build();
    }

//    @Transactional(readOnly = true)
//    public List<EnderecoEletrodomesticoDTO> findByParam(String nomeRua, String nomeBairro, String nomeMunicipio) {
//        try{
//            var endereco = enderecoRepository.findByRuaOrBairroOrMunicipio(nomeRua, nomeBairro, nomeMunicipio);
//            return endereco.stream().map(EnderecoEletrodomesticoDTO::new).collect(Collectors.toList());
//        } catch (EntityNotFoundException e) {
//            throw new ServiceNotFoundedException("Endereco não encontrada");
//        }
//    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<EnderecoEletrodomesticoDTO>> findByParam(String nomeRua, String nomeBairro, String nomeMunicipio) {
        var endereco = enderecoRepository.findByRuaOrBairroOrMunicipio(nomeRua, nomeBairro, nomeMunicipio);
        if (!endereco.isEmpty()){
            return ResponseEntity.ok(endereco.stream().map(EnderecoEletrodomesticoDTO::new).collect(Collectors.toList()));
        }
        return ResponseEntity.notFound().build();
    }

}
