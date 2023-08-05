package com.example.CaseItau.service;

import com.example.CaseItau.controller.CadastroController;
import com.example.CaseItau.model.Cadastro;
import com.example.CaseItau.repository.CadastroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CadastroServiceTest {

    @Mock
    private CadastroRepository cadastroRepository;

    private CadastroService cadastroService;

    @Mock
    List<Cadastro> mkCadastros;

    @Mock
    private Cadastro mkCadastro;

    @BeforeEach
    void configuracao(){
        cadastroService = new CadastroService(cadastroRepository);
    }

    @Test
    void deve_achar_um_cadastro_por_id() {
        Long id = any(Long.class);
        Optional<Cadastro> cadastroOptional = Optional.of(mkCadastro);
        Mockito.when(cadastroRepository.findById(id)).thenReturn(cadastroOptional);
        Optional<Cadastro> cadastro = cadastroService.listarCadastroId(id);
        assertEquals(cadastroOptional,cadastro);
    }

    @Test
    void deve_retornar_todos_os_cadastros() {
        Mockito.when(cadastroRepository.findAll()).thenReturn(mkCadastros);
        List<Cadastro> cadastro = cadastroService.listarCadastros();
        assertEquals(mkCadastros, cadastro);
    }

    @Test
    void deve_salvar_cadastro() {
        Mockito.when(cadastroRepository.save(mkCadastro)).thenReturn(mkCadastro);
        Cadastro cadastro = cadastroService.criarCadastro(mkCadastro);

        assertEquals(mkCadastro, cadastro);

    }

    @Test
    void deve_atualizar_um_cadastro() {
        Long id = 1L;
        Cadastro mkCadastro = new Cadastro();
        Map<Object, Object> data = Map.of("nome", "Debora");
        Mockito.when(cadastroRepository.findById(id)).thenReturn(Optional.of(mkCadastro));
        cadastroService.atualizarParcial(id, data);
        ArgumentCaptor<Cadastro> argumentCaptor = ArgumentCaptor.forClass(Cadastro.class);
        verify(cadastroRepository).save(argumentCaptor.capture());
        assertEquals("Debora", argumentCaptor.getValue().getNome());
    }

    @Test
    void deve_excluir_um_cadastro_existente() {
        Long id = 1L;
        Mockito.when(cadastroRepository.findById(id)).thenReturn(Optional.of(mkCadastro));
        boolean result = cadastroService.deletarCadastro(id);
        assertTrue(result);
    }

    @Test
    void nao_deve_excluir_um_cadastro_inexistente() {
        Long id = 1L;
        boolean result = cadastroService.deletarCadastro(id);
        assertFalse(result);
    }



}
