package com.example.springboot.dtos;


import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

public record AgendaRecordDto(@NotBlank String nome, @NotNull String email, @NotNull String telefone) {

}
