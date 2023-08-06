package com.example.springboot.controllers;

import com.example.springboot.repositories.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

//Anotação indica que será um bean a usar API REST
@RestController
public class AgendaController {


    //Injeção de dependencia
    @Autowired
    AgendaRepository agendaRepository;



}
