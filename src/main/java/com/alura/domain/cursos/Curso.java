package com.alura.domain.cursos;

import com.alura.domain.topico.Topico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "cursos")
@Entity(name = "Curso")
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String categoria;

	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	@JsonIgnore
	private List<Topico> topicos = new ArrayList<>();

	public Curso(String nombre, String categoria) {
		this.nombre = nombre;
		this.categoria = categoria;
	}

	public Curso(Long id,String nombre, String categoria) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.id = id;
	}

    public Curso(DatosRegistroCurso datosRegistroCurso) {
		this.nombre = datosRegistroCurso.nombre();
		this.categoria = datosRegistroCurso.categoria();
    }

	public void actualizaCurso(DatosActualizarCurso datosActualizarCurso) {
		if(datosActualizarCurso.nombre() != null) {
			this.nombre = datosActualizarCurso.nombre();
		}
		if (datosActualizarCurso.categoria() != null) {
			this.categoria = datosActualizarCurso.categoria();
		}

	}
}