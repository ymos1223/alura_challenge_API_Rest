package com.alura.domain.respuestas;

import com.alura.domain.usuarios.DatosMostrarUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DatosMostrarRespuesta(Long id,
                                    String mensaje,
                                    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                                    LocalDateTime fechaCreacion,
                                    DatosMostrarUsuario autor,
                                    Boolean solucion) {
}
