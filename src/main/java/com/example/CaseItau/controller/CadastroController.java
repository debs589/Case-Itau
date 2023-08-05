package com.example.CaseItau.controller;

import com.example.CaseItau.model.Cadastro;
import com.example.CaseItau.service.CadastroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
public class CadastroController {

    Logger logger = LoggerFactory.getLogger(CadastroController.class);

    private final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @GetMapping("/cadastro")
    public List<Cadastro> listarCadastros() {
        logger.info("Listando cadastros");
        return cadastroService.listarCadastros();
    }

    @GetMapping("/cadastro/{id}")
    public Optional<Cadastro> listarCadastroId(@PathVariable("id") Long id) {
        logger.info("Listando cadastros por id: " + id);
        return cadastroService.listarCadastroId(id);
    }

    @PostMapping("/cadastro")
    public Cadastro criarCadastro(@RequestBody Cadastro cadastro)
    {
        logger.info("Criando cadastro");
        return cadastroService.criarCadastro(cadastro);
    }

    @PatchMapping("/cadastro/{id}")
    public Cadastro atualizarParcial(@PathVariable("id") Long id, @RequestBody Map<Object, Object> data){
        logger.info("Atualizando cadastro por id: " + id);
        return cadastroService.atualizarParcial(id, data);
    }

    @DeleteMapping("/cadastro/{id}")
    public ResponseEntity<String> deletarCadastro(@PathVariable Long id) {
        logger.info("Deletando cadastro por id: " + id);
        if(cadastroService.deletarCadastro(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cadastro excluído com sucesso!");
        } else {
            logger.error("Id de Cadastro não encontrado: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro não encontrado!");
        }
    }

}
