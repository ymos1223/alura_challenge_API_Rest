package com.alura.domain.respuestas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta,Long> {
    List<Respuesta> findByTopicoId(Long idTopico);
}
