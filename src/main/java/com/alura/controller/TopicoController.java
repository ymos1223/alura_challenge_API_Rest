package com.alura.controller;

import com.alura.domain.cursos.Curso;
import com.alura.domain.cursos.CursoRepository;
import com.alura.domain.topico.*;
import com.alura.domain.usuarios.DatosMostrarUsuario;
import com.alura.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;
    @Autowired
    public TopicoController(TopicoRepository topicoRepository, CursoRepository cursoRepository,
                            UsuarioRepository usuarioRepository) {
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registroTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                               UriComponentsBuilder uriComponentsBuilder) {
        var curso = cursoRepository.getReferenceById(datosRegistroTopico.id_curso());
        var autor = usuarioRepository.getReferenceById(datosRegistroTopico.id_autor());
        var topico = topicoRepository.save(new Topico(datosRegistroTopico,curso,autor));
        var datosMostrarUsuario = new DatosMostrarUsuario(autor.getId(), autor.getNombre());
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(),topico.getTitulo(),topico.getMensaje(),
                topico.getFechaCreacion(),topico.getStatus().toString(), datosMostrarUsuario, curso);

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@PageableDefault(sort = "titulo", size = 8) Pageable paginacion) {
        Page<DatosListadoTopico> topicos= topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
        return ResponseEntity.ok(topicos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> buscarTopicoPorId(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var DatosTopico = new DatosListadoTopico(topico.getId(),topico.getTitulo(),topico.getMensaje(),
                topico.getFechaCreacion(),topico.getStatus().toString(), new DatosMostrarUsuario(topico.getAutor().getId(),topico.getAutor().getNombre()),new Curso(topico.getCurso().getNombre(),topico.getCurso().getCategoria()));
        return ResponseEntity.ok(DatosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        var topico = topicoRepository.getReferenceById(id);
        var curso = cursoRepository.getReferenceById(datosActualizarTopico.id_curso());
        topico.actualizarTopico(datosActualizarTopico);
        var DatosTopico = new DatosRespuestaTopico(topico.getId(),topico.getTitulo(),topico.getMensaje(),
                topico.getFechaCreacion(),topico.getStatus().toString(), new DatosMostrarUsuario(topico.getAutor().getId(),topico.getAutor().getNombre()),new Curso(topico.getCurso().getId(),topico.getCurso().getNombre(), topico.getCurso().getCategoria()));
        return ResponseEntity.ok(DatosTopico);
    }

}
