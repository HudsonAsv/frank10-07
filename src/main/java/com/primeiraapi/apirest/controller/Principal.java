package com.primeiraapi.apirest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Principal {
    @GetMapping
    public String metodo1(){
        return "estou no método 1";
    }
    @PutMapping
    public String metodo2(){
        return "estou no método 2";
    }
}
