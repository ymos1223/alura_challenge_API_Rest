package com.alura.controller;

import com.alura.domain.respuestas.DatosMostrarRespuesta;
import com.alura.domain.respuestas.DatosRegistroRespuesta;
import com.alura.domain.respuestas.Respuesta;
import com.alura.domain.respuestas.RespuestaRepository;
import com.alura.domain.topico.DatosTopicoConRespuestas;
import com.alura.domain.topico.Topico;
import com.alura.domain.topico.TopicoRepository;
import com.alura.domain.usuarios.DatosMostrarUsuario;
import com.alura.domain.usuarios.UsuarioRepository;
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
}
