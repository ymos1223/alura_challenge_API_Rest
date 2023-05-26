package com.alura.domain.cursos;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(@NotBlank String nombre, @NotBlank String categoria) {


}
