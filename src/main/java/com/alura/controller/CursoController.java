package com.alura.controller;


import com.alura.domain.cursos.*;
import com.alura.domain.topico.DatosListadoTopico;
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

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        Curso curso = cursoRepository.save(new Curso(datosRegistroCurso));
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria());

        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoCurso>> listarCursos(@PageableDefault(sort = "nombre", size = 8) Pageable paginacion) {
        Page<DatosListadoCurso> cursos = cursoRepository.findAll(paginacion).map(DatosListadoCurso::new);
        return ResponseEntity.ok(cursos);
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity eliminarCurso(@PathVariable Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        cursoRepository.delete(curso);
        return ResponseEntity.noContent().build();

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DatosListadoCurso> buscarPorId(@PathVariable Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        DatosListadoCurso cursoPorId = new DatosListadoCurso(curso);
        return ResponseEntity.ok(cursoPorId);
    }

    @PutMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaCurso> actualizarCurso(@PathVariable Long id,@RequestBody @Valid DatosActualizarCurso datosActualizarCurso) {
    var curso = cursoRepository.getReferenceById(id);
    curso.actualizaCurso(datosActualizarCurso);

    return ResponseEntity.ok(new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria()));
    }
}
