package com.alura.controller;

import com.alura.domain.usuarios.DatosAutenticacionUsuario;
import com.alura.domain.usuarios.Usuario;
import com.alura.infra.security.DatosJWTtoken;
import com.alura.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager,TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;

    }

        @PostMapping
        public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
            UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken
                    (datosAutenticacionUsuario.email(),
                            datosAutenticacionUsuario.contrasena());
            var usuarioAutenticado = authenticationManager.authenticate(authToken);
            var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

            return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
        }

    }

