package es.abelramirez.proyectofinalabel.service;



import es.abelramirez.proyectofinalabel.dto.request.LoginRequest;
import es.abelramirez.proyectofinalabel.dto.request.RegisterRequest;
import es.abelramirez.proyectofinalabel.dto.response.JugadorResponse;
import es.abelramirez.proyectofinalabel.mappers.request.JugadorMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.JugadorMapperResponse;
import es.abelramirez.proyectofinalabel.models.entities.Jugador;
import es.abelramirez.proyectofinalabel.repositories.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JugadorRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JugadorMapperResponse  jugadorMapperResponse;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final JugadorMapperRequest jugadorMapperRequest;

    public JugadorResponse obtenerPerfil(Authentication authentication) {
        String email = authentication.getName();
        return usuarioRepository
                .findByEmail(email)
                .map(jugadorMapperResponse::toDto)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        try {
            // Autenticar al usuario con email y password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
            );

            // Si las credenciales son correctas, generar token
            String token = jwtService.generateToken(authentication);
            return ResponseEntity.ok(Map.of("token", token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales incorrectas"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error en el servidor"));
        }
    }

    public ResponseEntity<?> register(RegisterRequest registerRequest) {
       if(usuarioRepository.findByEmail(registerRequest.email()).isPresent()) {
           return ResponseEntity
                   .status(HttpStatus.BAD_REQUEST)
                   .body(Map.of("error", "El email ya existe"));
       }
       Jugador jugador = jugadorMapperRequest.toEntity(registerRequest);
       jugador.setPassword(passwordEncoder.encode(registerRequest.password()));
       usuarioRepository.save(jugador);
       return ResponseEntity.ok(jugadorMapperResponse.toDto(jugador));
    }
}
