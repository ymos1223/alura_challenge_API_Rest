package com.alura.domain.topico;


import com.alura.domain.cursos.Curso;

public record DatosActualizarTopico(String titulo,
                                    String mensaje,
                                    StatusTopico status,
                                    Long id_curso ) {

}
