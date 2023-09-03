package com.fiap.techchallenge.energia.dominio.endereco.service;

import com.fiap.techchallenge.energia.dominio.endereco.dto.EnderecoDTO;
import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import com.fiap.techchallenge.energia.dominio.endereco.repository.IEnderecoRepository;
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
public class EnderecoService {

    private final IEnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(IEnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public EnderecoDTO save(EnderecoDTO enderecoDTO) {
        var enderecoEntity = enderecoDTO.toEntity();
        var enderecoSaved = enderecoRepository.save(enderecoEntity);
        return enderecoSaved.ToEnderecoDTO();
    }

    @Transactional(readOnly = true)
    public Page<EnderecoDTO> findAll(PageRequest pageRequest) { //alterar pelo DTO a ser criado para retornar o endereco vinculado as pessoas e eletrodomesticos
        var enderecos = enderecoRepository.findAll(pageRequest);
        return  enderecos.map(Endereco::ToEnderecoDTO); //alterar pelo DTO a ser criado para retornar o endereco vinculado as pessoas e eletrodomesticos
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO enderecoDTO) {
        try {
            Endereco enderecoEntity = enderecoRepository.getReferenceById(id);
            enderecoDTO.ToMapperEntity(enderecoEntity);

            var enderecoSaved = enderecoRepository.save(enderecoEntity);
            return enderecoSaved.ToEnderecoDTO();
        }  catch (EntityNotFoundException e) {
            throw new RuntimeException("Endereço não encontrado, id: " + id);
        }
    }

    @Transactional
    public void delete(Long id)  {
        try {
            enderecoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Violação de integridade dos dados");
        }
    }

    @Transactional(readOnly = true)
    public EnderecoDTO findById(Long id) {
        var endereco = enderecoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Endereço não encontrado")
        );
        return endereco.ToEnderecoDTO();
    }

    @Transactional(readOnly = true)
    public List<EnderecoDTO> findByParam(String nomeRua, String nomeBairro, String nomeMunicipio) {
        var endereco = enderecoRepository.findByRuaOrBairroOrMunicipio(nomeRua, nomeBairro, nomeMunicipio);
        return endereco.stream().map(EnderecoDTO::new).collect(Collectors.toList());
    }

}
