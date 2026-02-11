package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.JugadorRequest;
import es.abelramirez.proyectofinalabel.dto.response.JugadorResponse;
import es.abelramirez.proyectofinalabel.service.JugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jugadores")
@RequiredArgsConstructor
@Tag(name="Controlador de los jugadores",description = "Gestiona los jugadores")
public class JugadorController {
    private final JugadorService jugadorService;

    @GetMapping
    @Operation(summary = "Obtiene todos los jugadores",description = "GET")
    public ResponseEntity<List<JugadorResponse>> getAll() {
        return ResponseEntity.ok(jugadorService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un jugador especifico en base a su ID",description = "GET")
    public ResponseEntity<JugadorResponse> getById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(jugadorService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo jugador",description = "POST")
    public ResponseEntity<JugadorRequest> post(@RequestBody JugadorRequest categoriaRequest) {
        return ResponseEntity.ok(jugadorService.create(categoriaRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un usuario ya existente, lo busca en base a su ID",description = "PUT")
    public ResponseEntity<JugadorResponse> put(@Valid @PathVariable Long id,@RequestBody JugadorRequest categoriaRequest) {
        return ResponseEntity.ok(jugadorService.update(id,categoriaRequest));
    }

    @Operation(summary = "Borra un jugador, lo busca en base a su ID",description = "GET")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        jugadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
