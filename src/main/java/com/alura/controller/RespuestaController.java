package com.alura.controller;

import com.alura.domain.respuestas.*;
import com.alura.domain.topico.*;
import com.alura.domain.usuarios.DatosMostrarUsuario;
import com.alura.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    private final RespuestaRepository respuestaRepository;
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public RespuestaController(RespuestaRepository respuestaRepository, TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.respuestaRepository = respuestaRepository;
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/{idTopico}")
    public ResponseEntity<DatosTopicoConRespuestas> registrarRespuesta(@PathVariable Long idTopico, @RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,

                                                                       UriComponentsBuilder uriComponentsBuilder) {
        var autor = usuarioRepository.getReferenceById(datosRegistroRespuesta.id_autor());
        var topico = topicoRepository.getReferenceById(idTopico);
        var respuesta = respuestaRepository.save(new Respuesta(datosRegistroRespuesta, topico, autor));
        var datosMostarUsuario = new DatosMostrarUsuario(autor.getId(), autor.getNombre());
        var datosMostrarRespuesta = new DatosMostrarRespuesta(respuesta.getId(), respuesta.getMensaje(),
                respuesta.getFechaCreacion(), datosMostarUsuario, respuesta.getSolucion());
        DatosTopicoConRespuestas datosTopicoConRespuestas = new DatosTopicoConRespuestas(topico.getId(),
                topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), datosMostarUsuario,
                topico.getCurso(), List.of(datosMostrarRespuesta));
        URI url = uriComponentsBuilder.path("/{idTopico}/{idRespuesta}").buildAndExpand(topico.getId().toString(),
                respuesta.getId().toString()).toUri();
        return ResponseEntity.created(url).body(datosTopicoConRespuestas);
    }

    @GetMapping("/{idTopico}")
    public ResponseEntity<DatosTopicoConRespuestas> listarTopicoConRespuestas(@PathVariable Long idTopico) {
        Topico topico = topicoRepository.getReferenceById(idTopico);
        var respuestas = respuestaRepository.findByTopicoId(idTopico);

        List<DatosMostrarRespuesta> datosRespuestas = respuestas.stream()
                .map(respuesta -> new DatosMostrarRespuesta(respuesta.getId(), respuesta.getMensaje(),
                        respuesta.getFechaCreacion(), new DatosMostrarUsuario(respuesta.getAutor().getId(),
                        respuesta.getAutor().getNombre()), respuesta.getSolucion()))
                .collect(Collectors.toList());
        DatosTopicoConRespuestas datosTopicoConRespuestas = new DatosTopicoConRespuestas(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),
                new DatosMostrarUsuario(topico.getAutor().getId(), topico.getAutor().getNombre()),
                topico.getCurso(), datosRespuestas);
        return ResponseEntity.ok(datosTopicoConRespuestas);
    }

@GetMapping("/{idTopico}/{idRespuesta}")
    public ResponseEntity<DatosTopicoConRespuestaEspecifica> listarTopicoConIdEspecifico(@PathVariable Long idTopico,
                                                                                         @PathVariable Long idRespuesta) {
        var topico = topicoRepository.getReferenceById(idTopico);
        var respuesta = respuestaRepository.getReferenceById(idRespuesta);
    var datosMostrarRespuesta = new DatosMostrarRespuesta(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaCreacion(),
            new DatosMostrarUsuario(respuesta.getAutor().getId(),respuesta.getAutor().getNombre()),respuesta.getSolucion());
        var datosTopicoConRespuestaEspecifica = new DatosTopicoConRespuestaEspecifica(topico.getId(), topico.getTitulo(),
                topico.getMensaje(),topico.getFechaCreacion(),topico.getStatus(), new DatosMostrarUsuario(respuesta.getAutor().getId(),
                respuesta.getAutor().getNombre()),topico.getCurso(),List.of(datosMostrarRespuesta));

    return ResponseEntity.ok(datosTopicoConRespuestaEspecifica);
}

@DeleteMapping("{idTopico}/{idRespuesta}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long idTopico,
                                            @PathVariable Long idRespuesta) {
    var topico = topicoRepository.getReferenceById(idTopico);
    var respuesta = respuestaRepository.getReferenceById(idRespuesta);
    respuestaRepository.delete(respuesta);

    return ResponseEntity.noContent().build();

}
    @PutMapping("{idTopico}/{idRespuesta}")
    @Transactional
    public ResponseEntity<DatosTopicoConRespuestaActualizada> actualizarRespuesta(@PathVariable Long idTopico,
                                                                                  @PathVariable Long idRespuesta,
    @RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta) {

        var topico = topicoRepository.getReferenceById(idTopico);
        var respuesta = respuestaRepository.getReferenceById(idRespuesta);
        respuesta.actualizarRespuesta(datosActualizarRespuesta);
        var datosMostrarRespuesta = new DatosMostrarRespuesta(respuesta.getId(),respuesta.getMensaje(),respuesta.getFechaCreacion(),
                new DatosMostrarUsuario(respuesta.getAutor().getId(),respuesta.getAutor().getNombre()),respuesta.getSolucion());
        var datosTopicoConRespuestaActualizada = new DatosTopicoConRespuestaActualizada(topico.getId(),
                topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),topico.getStatus(),
                new DatosMostrarUsuario(topico.getAutor().getId(),topico.getAutor().getNombre()),topico.getCurso(),datosMostrarRespuesta);

            return ResponseEntity.ok(datosTopicoConRespuestaActualizada);

}
}