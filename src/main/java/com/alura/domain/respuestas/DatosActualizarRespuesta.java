package com.alura.domain.respuestas;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(String mensaje, Boolean solucion) {
}
