package com.example.springboot.models;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
//Define o nome a ser dado para a tabela no database
@Table(name = "TB_Agenda", uniqueConstraints = @UniqueConstraint(columnNames = "telefone"))

public class AgendaModel implements Serializable {

    private static final long serialVersionUID = 1L;
    //Utlizado para realizar o numero de controle de versão para quando for serializado

    //Define e demonstra como os valores serão colocados na ID da tabela do Banco
    @Id
    //Genereted irá gerar Ids para salvar no banco
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idContato;
    private String nome;
    private String email;
    @Column
    private String telefone;

    public UUID getIdContato() {
        return idContato;
    }

    public void setIdContato(UUID idContato) {
        this.idContato = idContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
