package com.fiap.techchallenge.energia.dominio.endereco.controller;

import com.fiap.techchallenge.energia.dominio.endereco.dto.EnderecoDTO;
import com.fiap.techchallenge.energia.dominio.endereco.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> save(@Valid @RequestBody EnderecoDTO dto) {
        var endereco = enderecoService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((endereco.getId())).toUri();
        return ResponseEntity.created(uri).body(endereco);
    }

    @GetMapping
    public ResponseEntity<Page<EnderecoDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage)
    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);
        var enderecoDTO = enderecoService.findAll(pageRequest);
        return ResponseEntity.ok().body(enderecoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> update(
            @Valid @RequestBody EnderecoDTO enderecoDTO,
            @PathVariable Long id) {
        var endereco = enderecoService.update(id, enderecoDTO);
        return ResponseEntity.ok(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id) {
        var endereco = enderecoService.findById(id);
        return ResponseEntity.ok(endereco);
    }
    @GetMapping("/{rua}/{bairro}/{municipio}")
    public ResponseEntity<EnderecoDTO> findByParam(@RequestParam String rua, String bairro, String municipio) {
        var endereco = enderecoService.findByParam(rua, bairro, municipio);
        return ResponseEntity.ok(endereco);
    }

}
