package com.fiap.techchallenge.energia.dominio.endereco.controller;

import com.fiap.techchallenge.energia.dominio.endereco.dto.EnderecoDTO;
import com.fiap.techchallenge.energia.dominio.endereco.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;

    }
    @GetMapping("/pesquisar")
    public ResponseEntity<List<EnderecoDTO>> pesquisarEndereco(
            @RequestParam(required = false) String rua,
            @RequestParam(required = false) String bairro,
            @RequestParam(required = false) String municipio
    ){

        if((rua == null || rua.isBlank())
                && (bairro == null || bairro.isBlank())
                && (municipio == null || municipio.isBlank())
        ){
            return ResponseEntity.badRequest().body(null);
        }

        return enderecoService.pesquisarEndereco(rua, bairro, municipio);
    }

}
