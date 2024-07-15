package com.desafioForoHub.Alura.controller;

import com.desafioForoHub.Alura.infra.security.AuthenticationResponse;
import com.desafioForoHub.Alura.infra.services.AuthenticationService;
import com.desafioForoHub.Alura.usuarios.AuthenticationRequest;
import com.desafioForoHub.Alura.usuarios.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/registrar")
    public ResponseEntity<AuthenticationResponse> registrarUsuario(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }


    @PostMapping("/autenticar")
    public ResponseEntity<AuthenticationResponse> verificarUsuario(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));

    }


}
