package com.example.springboot.repositories;

import com.example.springboot.models.AgendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

//Esta interface irá auxiliar a habilitar todos os métodos prontos do JPA para realizar os metodos HTTPS

//Repository indica que vai ser um bean gerenciado e que havera transações com bases de dados
@Repository
public interface AgendaRepository extends JpaRepository<AgendaModel, UUID> {

    List<AgendaModel> findByNome(String nome);



}
