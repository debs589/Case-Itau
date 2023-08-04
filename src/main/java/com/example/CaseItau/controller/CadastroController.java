package com.example.CaseItau.controller;

import com.example.CaseItau.model.Cadastro;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.CaseItau.service.CadastroService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    public Cadastro criarCadastro(@RequestBody Cadastro cadastro)
    {
        return cadastroService.criarCadastro(cadastro);
    }

    @PatchMapping("/cadastro/{id}")
    public Cadastro atualizarParcial(@PathVariable("id") Long id, @RequestBody Map<Object, Object> data){
        return cadastroService.atualizarParcial(id, data);
    }

    @DeleteMapping("/cadastro/{id}")
    public ResponseEntity<String> deletarCadastro(@PathVariable Long id) {
        if(cadastroService.deletarCadastro(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Conta excluída com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada!");
        }
    }

}
