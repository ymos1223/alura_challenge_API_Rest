package com.alura.domain.topico;

import com.alura.domain.cursos.Curso;
import com.alura.domain.usuarios.DatosMostrarUsuario;
import com.alura.domain.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DatosListadoTopico(Long id,String titulo, String mensaje,  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")LocalDateTime fechaCreacion,
                                 String estatus, DatosMostrarUsuario autor, Curso curso) {

    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus().toString(),
                new DatosMostrarUsuario(topico.getAutor().getId(),topico.getAutor().getNombre()), topico.getCurso());
    }
}
