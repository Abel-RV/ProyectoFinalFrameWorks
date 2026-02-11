package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.EnemigoRequest;
import es.abelramirez.proyectofinalabel.dto.response.EnemigoResponse;
import es.abelramirez.proyectofinalabel.service.EnemigoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enemigos")
@RequiredArgsConstructor
@Tag(name="Controlador de los enemigos",description = "Gestiona los enemigos")
public class EnemigoController {
    private final EnemigoService enemigoService;

    @Operation(summary = "Obtiene todos los enemigos",description = "GET")
    @GetMapping
    public ResponseEntity<List<EnemigoResponse>> getAll() {
        return ResponseEntity.ok(enemigoService.findAll());
    }

    @Operation(summary = "Obtiene un enemigo especifico buscandolo por su ID",description = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<EnemigoResponse> getById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(enemigoService.findById(id));
    }

    @Operation(summary = "Crea un enemigo nuevo",description = "POST")
    @PostMapping
    public ResponseEntity<EnemigoRequest> post(@RequestBody EnemigoRequest categoriaRequest) {
        return ResponseEntity.ok(enemigoService.create(categoriaRequest));
    }

    @Operation(summary = "Modifica un enemigo existente, lo busca mediante su ID",description = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<EnemigoResponse> put(@Valid @PathVariable Long id,@RequestBody EnemigoRequest categoriaRequest) {
        return ResponseEntity.ok(enemigoService.update(id,categoriaRequest));
    }

    @Operation(summary = "Borra un enemigo, lo busca mediante su ID",description = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        enemigoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
