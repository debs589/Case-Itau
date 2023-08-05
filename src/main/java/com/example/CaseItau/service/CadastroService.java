package com.example.CaseItau.service;

import com.example.CaseItau.repository.CadastroRepository;
import com.example.CaseItau.model.Cadastro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class CadastroService {

    private final CadastroRepository cadastroRepository;

    public CadastroService(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    public Optional<Cadastro> listarCadastroId(Long id) {
        return cadastroRepository.findById(id);
    }

    public List<Cadastro> listarCadastros() {
        return cadastroRepository.findAll();
    }

    public Cadastro criarCadastro(Cadastro cadastro) {
        return cadastroRepository.save(cadastro);
    }

    public Cadastro atualizarParcial(Long id, Map<Object, Object> objectMap) {
        Cadastro cadastro = cadastroRepository.findById(id).get();
        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Cadastro.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, cadastro, value);
        });
        return cadastroRepository.save(cadastro);
    }

    public boolean deletarCadastro(Long id) {
        return cadastroRepository.findById(id)
                .map(cadastro -> {
                    cadastroRepository.delete(cadastro);
                    return true;
                }).orElse(false);
    }

}
