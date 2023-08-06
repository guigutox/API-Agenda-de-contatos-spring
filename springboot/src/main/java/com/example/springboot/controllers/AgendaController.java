package com.example.springboot.controllers;

import com.example.springboot.dtos.AgendaRecordDto;
import com.example.springboot.models.AgendaModel;
import com.example.springboot.repositories.AgendaRepository;
import com.example.springboot.utils.Response;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Anotação indica que será um bean a usar API REST
@RestController
public class AgendaController {


    //Injeção de dependencia
    @Autowired
    AgendaRepository agendaRepository;

    @PostMapping("/agenda")
    public ResponseEntity<AgendaModel> saveContact(@RequestBody @Valid AgendaRecordDto agendaRecordDto){
        var agendaModel = new AgendaModel();
        BeanUtils.copyProperties(agendaRecordDto, agendaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaRepository.save(agendaModel));
    }

    @GetMapping("/agenda")
    public ResponseEntity <List<AgendaModel>> getAllContacts(){
        return ResponseEntity.status(HttpStatus.OK).body(agendaRepository.findAll());
    }

    @GetMapping("/agenda/{nome}")
    public ResponseEntity <Response<List<AgendaModel>>> getContactbyName(@PathVariable(value = "nome") String nome){
        List<AgendaModel> foundContacts = agendaRepository.findByNome(nome);
        Response<List<AgendaModel>> response = new Response<>();
        if(foundContacts.isEmpty()){
            response.getErrors().add("Contact not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setData(foundContacts);
        return ResponseEntity.status(HttpStatus.OK).body((Response<List<AgendaModel>>) response);
    }


}
