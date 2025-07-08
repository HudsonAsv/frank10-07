package com.primeiraapi.apirest.controller;

import com.primeiraapi.apirest.service.CarregaBancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carregabanco")
public class CarregaBancoController {

    @Autowired
    private CarregaBancoService carregaBancoService;

    @PostMapping("/executar")
    public String executarCarga() {
        carregaBancoService.carregarDadosCsv("src\\main\\resources\\alunostads.csv");
        return "Carga executada com sucesso, meu Lorde Demônio!";
    }
}