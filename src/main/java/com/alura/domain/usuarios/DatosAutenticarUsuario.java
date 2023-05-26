package com.alura.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAutenticarUsuario(@NotBlank
                                     @Email String email,
                                     @NotBlank
                                     String contraseña) {
}
