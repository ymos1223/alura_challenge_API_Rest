package com.alura.domain.topico;


import com.alura.domain.cursos.Curso;
import com.alura.domain.usuarios.DatosMostrarUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(Long id,
                                   String titulo,
                                   String mensaje,
                                   @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                                   LocalDateTime fechaCreacion,
                                   String estatus,

                                   DatosMostrarUsuario autor,

                                   Curso curso) {

}
