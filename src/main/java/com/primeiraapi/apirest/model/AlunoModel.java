package com.primeiraapi.apirest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class AlunoModel extends PessoaModel {

    private String campus;
    private String coordenacao;
    private String curso;
    private String situacao;
    private String periodoEntrada;

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCoordenacao() {
        return coordenacao;
    }

    public void setCoordenacao(String coordenacao) {
        this.coordenacao = coordenacao;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getPeriodoEntrada() {
        return periodoEntrada;
    }

    public void setPeriodoEntrada(String periodoEntrada) {
        this.periodoEntrada = periodoEntrada;
    }
}