package com.fiap.techchallenge.energia.dominio.eletrodomestico.controller;

import com.fiap.techchallenge.energia.dominio.eletrodomestico.dto.request.EletrodomesticoRequestDTO;
import com.fiap.techchallenge.energia.dominio.eletrodomestico.dto.response.EletrodomesticoDTO;
import com.fiap.techchallenge.energia.dominio.eletrodomestico.service.EletrodomesticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/eletrodomestico")
public class EletrodomesticoController {
    private final EletrodomesticoService eletrodomesticoService;

    @Autowired
    public EletrodomesticoController(EletrodomesticoService eletrodomesticoService) {
        this.eletrodomesticoService = eletrodomesticoService;
    }

    @PostMapping
    public ResponseEntity<EletrodomesticoDTO> cadastrarEletrodomestico(@RequestBody EletrodomesticoRequestDTO dto) {
        var eletrodomestico = eletrodomesticoService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((eletrodomestico.getId())).toUri();
        return ResponseEntity.created(uri).body(eletrodomestico);
    }

    @GetMapping
    public ResponseEntity<Page<EletrodomesticoDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);
        var enderecoDTO = eletrodomesticoService.findAll(pageRequest);
        return ResponseEntity.ok().body(enderecoDTO);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<EletrodomesticoDTO> findById(@PathVariable Long id) {
//        var eletromestico = eletrodomesticoService.findById(id);
//        return ResponseEntity.ok(eletromestico);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<EletrodomesticoDTO> findById(@PathVariable Long id) {
        return eletrodomesticoService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EletrodomesticoDTO> update(
            @Valid @RequestBody EletrodomesticoRequestDTO dto,
            @PathVariable Long id) {
        var eletromestico = eletrodomesticoService.update(id, dto);
        return ResponseEntity.ok(eletromestico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eletrodomesticoService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/Pesquisar")
//    public ResponseEntity<List<EletrodomesticoDTO>> findByParam(
//            @RequestParam(required = false) String nome,
//            @RequestParam(required = false) String modelo,
//            @RequestParam(required = false) Integer potencia
//    ) {
//        var eletrodomesticos = eletrodomesticoService.findByParam(nome, modelo, potencia);
//        return ResponseEntity.ok(eletrodomesticos);
//    }

    @GetMapping("/Pesquisar")
    public ResponseEntity<List<EletrodomesticoDTO>> findByParam(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) Integer potencia
    ) {
        return eletrodomesticoService.findByParam(nome, modelo, potencia);
    }

    @GetMapping("/calcularConsumo")
    public ResponseEntity calcularConsumo(@RequestParam Long id, @RequestParam Long minutos) {
        return eletrodomesticoService.retornarConsumo(id, minutos);
    }

}