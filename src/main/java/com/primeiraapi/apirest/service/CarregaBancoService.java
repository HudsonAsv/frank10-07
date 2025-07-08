package com.primeiraapi.apirest.service;

import com.primeiraapi.apirest.model.AlunoModel;
import com.primeiraapi.apirest.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class CarregaBancoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public void carregarDadosCsv(String caminhoArquivo) {
        List<AlunoModel> alunos = new ArrayList<>();

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(caminhoArquivo), "UTF-8"))) {
            String linha = bf.readLine();
            String[] headers = linha.split(",");

            Map<String, Integer> campos = new HashMap<>();
            for (String campo : List.of(
                    "campus", "coordenacao", "curso", "nome_estudante", "situacao",
                    "idade", "sexo", "email_institucional", "periodo_entrada")) {
                campos.put(campo, Arrays.asList(headers).indexOf(campo));
            }

            while ((linha = bf.readLine()) != null) {
                String[] data = linha.split(",");
                AlunoModel aluno = new AlunoModel();

                if (campos.get("campus") >= 0) aluno.setCampus(data[campos.get("campus")]);
                if (campos.get("coordenacao") >= 0) aluno.setCoordenacao(data[campos.get("coordenacao")]);
                if (campos.get("curso") >= 0) aluno.setCurso(data[campos.get("curso")]);
                if (campos.get("situacao") >= 0) aluno.setSituacao(data[campos.get("situacao")]);
                if (campos.get("periodo_entrada") >= 0) aluno.setPeriodoEntrada(data[campos.get("periodo_entrada")]);

                if (campos.get("nome_estudante") >= 0) aluno.setNome(data[campos.get("nome_estudante")]);
                if (campos.get("idade") >= 0 && !data[campos.get("idade")].isBlank()) {
                    aluno.setIdade(Integer.parseInt(data[campos.get("idade")]));
                }
                if (campos.get("sexo") >= 0) aluno.setSexo(data[campos.get("sexo")]);
                if (campos.get("email_institucional") >= 0) aluno.setEmailInstitucional(data[campos.get("email_institucional")]);

                alunos.add(aluno);
            }

            alunoRepository.saveAll(alunos);
            System.out.println("Dados inseridos com sucesso, meu Lorde DemÃ´nio.");
        } catch (Exception e) {
            System.err.println("Erro ao carregar o arquivo CSV: " + e.getMessage());
        }
    }
}