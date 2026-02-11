package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.PersonajeRequest;
import es.abelramirez.proyectofinalabel.dto.response.PersonajeResponse;
import es.abelramirez.proyectofinalabel.service.PersonajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personajes")
@RequiredArgsConstructor
@Tag(name="Controlador de los personajes",description = "Gestiona los personajes jugables")
public class PersonajeController {
    private final PersonajeService personajeService;

    @GetMapping
    @Operation(summary = "Obtiene todos los personajes",description = "GET")
    public ResponseEntity<List<PersonajeResponse>> getAll() {
        return ResponseEntity.ok(personajeService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un personaje espefico en base a su ID",description = "GET")
    public ResponseEntity<PersonajeResponse> getById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(personajeService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo personaje",description = "POST")
    public ResponseEntity<PersonajeRequest> post(@RequestBody PersonajeRequest categoriaRequest) {
        return ResponseEntity.ok(personajeService.create(categoriaRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita un personaje ya existente, lo busca en base a su ID",description = "PUT")
    public ResponseEntity<PersonajeResponse> put(@Valid @PathVariable Long id,@RequestBody PersonajeRequest categoriaRequest) {
        return ResponseEntity.ok(personajeService.update(id,categoriaRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borra un personaje ya existente, lo busca en base a su ID",description = "DELETE")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        personajeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
