package com.alura.domain.usuarios;

import com.alura.domain.respuestas.Respuesta;
import com.alura.domain.topico.Topico;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String email;
	private String contrasena;

	private Boolean activo;

	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Topico> topicos = new ArrayList<>();
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Respuesta> respuestas = new ArrayList<>();


	public Usuario(String nombre, String email, String contrasenaEncriptada) {
		this.nombre = nombre;
		this.email = email;
		this.activo = true;
		this.contrasena = contrasenaEncriptada;
    }

	public Usuario(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return contrasena;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void desactivarUsuario() {
		this.activo = false;
	}

	public void actualizarUsuario(DatosActualizadosUsuario datosActualizadosUsuario) {

		if (datosActualizadosUsuario.nombre() != null) {
			this.nombre = datosActualizadosUsuario.nombre();
		}

		if (datosActualizadosUsuario.email() != null) {
			this.email = datosActualizadosUsuario.email();
		}

		if (datosActualizadosUsuario.contrasena() != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String contrasenaEncriptada = passwordEncoder.encode(datosActualizadosUsuario.contrasena());
			this.contrasena = contrasenaEncriptada;
		}
	}
}