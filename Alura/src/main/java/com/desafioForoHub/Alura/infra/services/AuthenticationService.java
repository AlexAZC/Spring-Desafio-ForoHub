package com.desafioForoHub.Alura.infra.services;

import com.desafioForoHub.Alura.infra.security.AuthenticationResponse;
import com.desafioForoHub.Alura.repositories.UsuarioRepository;
import com.desafioForoHub.Alura.usuarios.AuthenticationRequest;
import com.desafioForoHub.Alura.usuarios.RegisterRequest;
import com.desafioForoHub.Alura.usuarios.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        var user = Usuario.builder()
                .login(request.getLogin())
                .clave(request.getClave())
                .build();
        usuarioRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getClave()
                )
        );

        var user = usuarioRepository.findByLogin(request.getLogin());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

}
