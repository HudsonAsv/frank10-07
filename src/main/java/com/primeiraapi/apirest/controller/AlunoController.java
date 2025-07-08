package com.primeiraapi.apirest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.primeiraapi.apirest.model.AlunoModel;
import com.primeiraapi.apirest.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{id}")
    public ResponseEntity<AlunoModel> buscarPorId(@PathVariable Long id){
        Optional<AlunoModel> aluno = alunoService.buscarPorId(id);
        if (aluno.isPresent()) {
            return new ResponseEntity<>(aluno.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AlunoModel(), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody AlunoModel aluno){
        String retorno = alunoService.salvar(aluno);
        try {
            return new ResponseEntity<>(retorno, HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//salvar
//buscar Tudo
//buscar por id
//buscar por nome
//editar
//remover
}
