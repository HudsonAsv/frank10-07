package com.primeiraapi.apirest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primeiraapi.apirest.model.AlunoModel;
import com.primeiraapi.apirest.repository.AlunoRepository;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public String salvar(AlunoModel aluno) {
        try {
            alunoRepository.saveAndFlush(aluno);
            return "Salvo com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Optional<AlunoModel> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public List<AlunoModel> buscarTodos() {
        return alunoRepository.findAll();
    }

    public List<AlunoModel> buscarPorNome(String nome) {
        return alunoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public String editar(Long id, AlunoModel alunoAtualizado) {
        Optional<AlunoModel> alunoExistente = alunoRepository.findById(id);
        if (alunoExistente.isPresent()) {
            AlunoModel aluno = alunoExistente.get();
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setIdade(alunoAtualizado.getIdade());
            aluno.setSexo(alunoAtualizado.getSexo());
            aluno.setEmailInstitucional(alunoAtualizado.getEmailInstitucional());
            aluno.setCampus(alunoAtualizado.getCampus());
            aluno.setCoordenacao(alunoAtualizado.getCoordenacao());
            aluno.setCurso(alunoAtualizado.getCurso());
            aluno.setSituacao(alunoAtualizado.getSituacao());
            aluno.setPeriodoEntrada(alunoAtualizado.getPeriodoEntrada());
            alunoRepository.saveAndFlush(aluno);
            return "Atualizado com sucesso!";
        }
        return "Aluno não encontrado";
    }

    public String remover(Long id) {
        Optional<AlunoModel> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            alunoRepository.deleteById(id);
            return "Removido com sucesso!";
        }
        return "Aluno não encontrado";
    }
}
