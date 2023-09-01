package com.fiap.techchallenge.energia.dominio.pessoa.controller;

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

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;

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
