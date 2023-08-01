package com.example.CaseItau.controller;

import com.example.CaseItau.model.Cadastro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.CaseItau.service.CadastroService;

import java.util.List;
import java.util.Optional;

@RestController
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @GetMapping("/cadastro")
    public List<Cadastro> listarCadastros() {
        return cadastroService.listarCadastros();
    }

    @GetMapping("/cadastro/{id}")
    public Optional<Cadastro> listarCadastroId(@PathVariable("id") Long id) {
        return cadastroService.listarCadastroId(id);
    }

    @PostMapping("/cadastro")
    public Cadastro criarCadastro(@RequestBody Cadastro cadastro) {
        return cadastroService.criarCadastro(cadastro);
    }
}
