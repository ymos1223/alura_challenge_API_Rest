package com.alura.domain.respuestas;

import com.alura.domain.topico.Topico;
import com.alura.domain.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "respuestas")
@Entity(name = "Respuesta")
public class Respuesta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensaje;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_topico", nullable = false)
	@JsonBackReference
	private Topico topico;
	@Column(name = "fecha_creacion")
	private LocalDateTime fechaCreacion;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_autor", nullable = false)
	@JsonBackReference
	private Usuario autor;
	private Boolean solucion;

	public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta, Topico topico, Usuario autor) {
		this.mensaje = datosRegistroRespuesta.mensaje();
		this.topico = topico;
		this.fechaCreacion = LocalDateTime.now();
		this.autor = autor;
		this.solucion = false;
	}
}