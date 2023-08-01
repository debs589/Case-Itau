package com.example.CaseItau.service;

import com.example.CaseItau.repository.CadastroRepository;
import com.example.CaseItau.model.Cadastro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {

    @Autowired
    private CadastroRepository cadastroRepository;

    public Optional<Cadastro> listarCadastroId(Long id){
        return cadastroRepository.findById(id);
    }

    public List<Cadastro> listarCadastros() {
        return cadastroRepository.findAll();
    }

    public Cadastro criarCadastro(Cadastro cadastro) {
        return cadastroRepository.save(cadastro);
    }

    @Transactional
    public boolean deletarCadastro(Long id) {
        return cadastroRepository.findById(id)
                .map(cadastro -> {
                    cadastroRepository.delete(cadastro);
                    return true;
                }).orElse(false);
    }

}
