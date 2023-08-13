package com.example.springboot.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AgendaRecordDto(@NotBlank String nome, @NotNull String email, @NotNull String telefone) {

}
