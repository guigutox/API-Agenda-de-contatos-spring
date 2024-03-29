package com.example.springboot.controllers;

import com.example.springboot.dtos.AgendaRecordDto;
import com.example.springboot.models.AgendaModel;
import com.example.springboot.repositories.AgendaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.utils.Response;

import java.util.List;
import java.util.Optional;

//Anotação indica que será um bean a usar API REST
@RestController
@CrossOrigin("*")
public class ContatoController {


    //Injeção de dependencia
    @Autowired
    AgendaRepository agendaRepository;

    @PostMapping("/contatos")
    public ResponseEntity<AgendaModel> saveContact(@RequestBody @Valid AgendaRecordDto agendaRecordDto){
        var agendaModel = new AgendaModel();
        BeanUtils.copyProperties(agendaRecordDto, agendaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaRepository.save(agendaModel));
    }

    @GetMapping("/contatos")
    public ResponseEntity <List<AgendaModel>> getAllContacts(){
        return ResponseEntity.status(HttpStatus.OK).body(agendaRepository.findAll());
    }

  /*  @GetMapping("/contatos/{nome}")
    public ResponseEntity <Response<List<AgendaModel>>> getContactbyName(@PathVariable(value = "nome") String nome){
        List<AgendaModel> foundContacts = agendaRepository.findByNome(nome);
        Response<List<AgendaModel>> response = new Response<>();
        if(foundContacts.isEmpty()){
            response.getErrors().add("Contact not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setData(foundContacts);
        return ResponseEntity.status(HttpStatus.OK).body((Response<List<AgendaModel>>) response);
    }*/


   @GetMapping("/contatos/{telefone}")
    public ResponseEntity<Response<AgendaModel>> getContactbyTelefone(@PathVariable(value = "telefone") String telefone){
       // Optional<AgendaModel> contactO = Optional.ofNullable(agendaRepository.findByTelefone(telefone));
       Optional<AgendaModel> foundContact = agendaRepository.findByTelefone(telefone);
       Response <AgendaModel> response = new Response<>() ;
       if(foundContact.isEmpty()){
           response.getErrors().add("Contact no found");
           return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
       }
        response.setData(foundContact.get());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/agenda/{nome}")
    public ResponseEntity<Object> updateContato(@PathVariable(value="nome") String nome, @RequestBody @Valid AgendaRecordDto agendaRecordDto){
            List<AgendaModel> contactO = agendaRepository.findByNome(nome);
            Response<List<AgendaModel>> response = new Response<>();
        if(contactO.isEmpty()){
            response.getErrors().add("Contact not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        //Como contact é um array ele vai pegar o elemento de index 0 apenas.
        var agendaModel = contactO.get(0);
        BeanUtils.copyProperties(agendaRecordDto, agendaModel);
        return ResponseEntity.status(HttpStatus.OK).body(agendaRepository.save(agendaModel));
    }

    @DeleteMapping("/agenda/{telefone}")
    public ResponseEntity<Object> deleteContato(@PathVariable(value = "telefone") String telefone){
        Optional<AgendaModel> foundContact = agendaRepository.findByTelefone(telefone);
        Response<List<AgendaModel>> response = new Response<>();
        if(foundContact.isEmpty()){
            response.getErrors().add("Contact not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        agendaRepository.delete(foundContact.get());
        return ResponseEntity.status(HttpStatus.OK).body("Contact deleted sucessfully");
    }


}
