package com.fiap.techchallenge.energia.dominio.endereco.controller;

import com.fiap.techchallenge.energia.dominio.endereco.dto.EnderecoDTO;
import com.fiap.techchallenge.energia.dominio.endereco.service.EnderecoService;
import com.fiap.techchallenge.energia.dominio.pessoa.dto.PessoaDTO;
import com.fiap.techchallenge.energia.dominio.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;

    }
    @GetMapping("/pesquisar")
    public ResponseEntity<List<EnderecoDTO>> pesquisarPessoa(
            @RequestParam(required = false) String rua,
            @RequestParam(required = false) String bairro,
            @RequestParam(required = false) String cidade
    ){

        if((rua == null || rua.isBlank())
                && (bairro == null || bairro.isBlank())
                && (cidade == null || cidade.isBlank())
        ){
            return ResponseEntity.badRequest().body(null);
        }

        return enderecoService.pesquisarEndereco(rua, bairro, cidade);
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage)
    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);
        var pessoasDTO = pessoaService.findAll(pageRequest);
        return ResponseEntity.ok().body(pessoasDTO);

    }

    @PostMapping
    public ResponseEntity<PessoaDTO> save(@Valid @RequestBody PessoaDTO dto) {
        var pessoa = pessoaService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((pessoa.getId())).toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        var pessoa = pessoaService.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(
            @Valid @RequestBody PessoaDTO dto,
            @PathVariable Long id) {
        var pessoa = pessoaService.update(id, dto);
        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
