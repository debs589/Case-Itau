package com.example.CaseItau.controller;

import com.example.CaseItau.model.Cadastro;
import com.example.CaseItau.service.CadastroService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CadastroControllerTest {

    private CadastroController cadastroController;

    @Mock
    private CadastroService cadastroService;

    @Mock
    List<Cadastro> mkCadastros;

    @Mock
    private Cadastro mkCadastro;

    @BeforeEach
    void configuracao(){
        cadastroController = new CadastroController(cadastroService);
    }

    @Test
    void deve_salvar_cadastro() {
        Mockito.when(cadastroService.criarCadastro(mkCadastro)).thenReturn(mkCadastro);
        Cadastro cadastro = cadastroController.criarCadastro(mkCadastro);

        assertEquals(mkCadastro, cadastro);

    }

    @Test
    void deve_retornar_todos_os_cadastros() {
        Mockito.when(cadastroService.listarCadastros()).thenReturn(mkCadastros);
        List<Cadastro> cadastro = cadastroController.listarCadastros();
        assertEquals(mkCadastros, cadastro);
    }

    @Test
    void deve_achar_um_cadastro_por_id() {
        Long id = any(Long.class);
        Optional<Cadastro> cadastroOptional = Optional.of(mkCadastro);
        Mockito.when(cadastroService.listarCadastroId(id)).thenReturn(cadastroOptional);
        Optional<Cadastro> cadastro = cadastroController.listarCadastroId(id);
        assertEquals(cadastroOptional,cadastro);
    }

    @Test
    void deve_excluir_um_cadastro_existente() {
        Long id = 1L;
        Mockito.when(cadastroService.deletarCadastro(id)).thenReturn(true);
        ResponseEntity<String> result = cadastroController.deletarCadastro(id);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        assertEquals("Cadastro excluído com sucesso!", result.getBody());
    }

    @Test
    void nao_deve_excluir_um_cadastro_inexistente() {
        Long id = 1L;
        Mockito.when(cadastroService.deletarCadastro(id)).thenReturn(false);
        ResponseEntity<String> result = cadastroController.deletarCadastro(id);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("Cadastro não encontrado!", result.getBody());
    }

    @Test
    void deve_atualizar_um_cadastro() {
        Long id = 1L;
        Map<Object, Object> data = new HashMap<>();
        Mockito.when(cadastroService.atualizarParcial(id, data)).thenReturn(mkCadastro);
        Cadastro cadastro = cadastroController.atualizarParcial(id, data);
        assertEquals(mkCadastro, cadastro);
    }
}
