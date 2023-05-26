package com.alura.domain.topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.alura.domain.cursos.Curso;
import com.alura.domain.respuestas.Respuesta;
import com.alura.domain.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "topicos")
@Entity(name = "Topico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensaje;
	@Column(name = "fecha_creacion")
	private LocalDateTime fechaCreacion;
	@Column(name = "estatus")
	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_autor", nullable = false)
	@JsonBackReference
	private Usuario autor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_curso", nullable = false)
	@JsonBackReference
	private Curso curso;
	@OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Respuesta> respuestas = new ArrayList<>();

	public Topico(DatosRegistroTopico datosRegistroTopico, Curso curso, Usuario autor) {
		this.titulo = datosRegistroTopico.titulo();
		this.mensaje = datosRegistroTopico.mensaje();
		this.status = StatusTopico.NO_RESPONDIDO;
		this.fechaCreacion = LocalDateTime.now();
		this.curso = curso;
		this.autor = autor;
	}

	public void actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
		if (datosActualizarTopico.titulo() != null) {
			this.titulo = datosActualizarTopico.titulo();
		}

		if (datosActualizarTopico.mensaje() != null) {
			this.mensaje = datosActualizarTopico.mensaje();
		}
		if (datosActualizarTopico.status() != null) {
			this.status = datosActualizarTopico.status();
		}
		if (datosActualizarTopico.id_curso() != null) {
			this.curso = curso;
		}

	}
}