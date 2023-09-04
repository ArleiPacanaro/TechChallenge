package com.fiap.techchallenge.energia.dominio.usuario.controller;

import com.fiap.techchallenge.energia.dominio.pessoa.service.PessoaService;
import com.fiap.techchallenge.energia.dominio.usuario.dto.request.UsuarioRequestDTO;
import com.fiap.techchallenge.energia.dominio.usuario.dto.response.UsuarioDTO;
import com.fiap.techchallenge.energia.dominio.usuario.dto.response.UsuarioPessoaDTO;
import com.fiap.techchallenge.energia.dominio.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioRequestDTO dto) {
        var usuario = usuarioService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((usuario.getId())).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioPessoaDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage)
    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);
        var userpessoasDTO = usuarioService.findAll(pageRequest);
        return ResponseEntity.ok().body(userpessoasDTO);

    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UsuarioPessoaDTO> findById(@PathVariable Long id) {
//        var usuario = usuarioService.findById(id);
//        return ResponseEntity.ok(usuario);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioPessoaDTO> findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(
            @Valid @RequestBody UsuarioRequestDTO dto,
            @PathVariable Long id) {
        var usuario = usuarioService.update(id, dto);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
