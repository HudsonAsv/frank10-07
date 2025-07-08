package com.primeiraapi.apirest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.primeiraapi.apirest.model.AlunoModel;
import com.primeiraapi.apirest.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    // buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<AlunoModel> buscarPorId(@PathVariable Long id) {
        Optional<AlunoModel> aluno = alunoService.buscarPorId(id);
        if (aluno.isPresent()) {
            return new ResponseEntity<>(aluno.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AlunoModel(), HttpStatus.NOT_FOUND);
    }

    // salvar
    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody AlunoModel aluno) {
        String retorno = alunoService.salvar(aluno);
        try {
            return new ResponseEntity<>(retorno, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // buscar tudo
    @GetMapping
    public ResponseEntity<List<AlunoModel>> buscarTodos() {
        List<AlunoModel> lista = alunoService.buscarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // buscar por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<AlunoModel>> buscarPorNome(@PathVariable String nome) {
        List<AlunoModel> lista = alunoService.buscarPorNome(nome);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // editar
    @PutMapping("/{id}")
    public ResponseEntity<String> editar(@PathVariable Long id, @Valid @RequestBody AlunoModel alunoAtualizado) {
        String retorno = alunoService.editar(id, alunoAtualizado);
        if (retorno.equals("Aluno não encontrado")) {
            return new ResponseEntity<>(retorno, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    // remover
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        String retorno = alunoService.remover(id);
        if (retorno.equals("Aluno não encontrado")) {
            return new ResponseEntity<>(retorno, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }
}
