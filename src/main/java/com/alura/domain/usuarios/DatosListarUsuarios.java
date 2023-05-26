package com.alura.domain.usuarios;

public record DatosListarUsuarios(Long id,String nombre,String email) {

    public DatosListarUsuarios(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}
