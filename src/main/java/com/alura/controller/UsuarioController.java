package com.alura.controller;


import com.alura.domain.usuarios.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {

        this.usuarioRepository =usuarioRepository;
    }

    @PostMapping("/registro")
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
    UriComponentsBuilder uriComponentsBuilder  ) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(datosRegistroUsuario.contrasena());
      var usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario.nombre(),datosRegistroUsuario.email(),passwordEncriptada));
      DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail());
        URI url = uriComponentsBuilder.path("/{id}").buildAndExpand(usuario.getId()).toUri();
      return ResponseEntity.created(url).body(datosRespuestaUsuario);
  }

    @GetMapping
    public ResponseEntity<Page<DatosListarUsuarios>> listarUsuarios(@PageableDefault(sort = "nombre", size = 8) Pageable paginacion) {
    Page<DatosListarUsuarios> usuarios = usuarioRepository.findByActivoTrue(paginacion).map(DatosListarUsuarios::new);
    return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.desactivarUsuario();
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DatosRespuestaUsuario> buscarUsuarioPorId(@PathVariable Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(),
                usuario.getEmail());
        return ResponseEntity.ok(datosRespuestaUsuario);
    }

    @PutMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(@PathVariable Long id, @RequestBody @Valid DatosActualizadosUsuario datosActualizadosUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.actualizarUsuario(datosActualizadosUsuario);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail()));
    }

}
