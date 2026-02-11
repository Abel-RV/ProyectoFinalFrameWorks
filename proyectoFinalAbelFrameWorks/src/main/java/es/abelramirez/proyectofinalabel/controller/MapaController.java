package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.MapaRequest;
import es.abelramirez.proyectofinalabel.dto.response.MapaResponse;
import es.abelramirez.proyectofinalabel.service.MapaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mapas")
@RequiredArgsConstructor
@Tag(name="Controlador de los mapas",description = "Gestiona los mapas")
public class MapaController {
    private final MapaService mapaService;

    @GetMapping
    @Operation(summary = "Obtiene todos los mapas",description = "GET")
    public ResponseEntity<List<MapaResponse>> getAll() {
        return ResponseEntity.ok(mapaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un mapa especifico en base a su ID",description = "GET")
    public ResponseEntity<MapaResponse> getById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(mapaService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crea un jugador nuevo",description = "POST")
    public ResponseEntity<MapaRequest> post(@RequestBody MapaRequest categoriaRequest) {
        return ResponseEntity.ok(mapaService.create(categoriaRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita un jugador ya existente, lo busca en base a su ID",description = "PUT")
    public ResponseEntity<MapaResponse> put(@Valid @PathVariable Long id,@RequestBody MapaRequest categoriaRequest) {
        return ResponseEntity.ok(mapaService.update(id,categoriaRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borra un jugador ya existente, lo busca en base a su ID",description = "DELETE")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mapaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}/objetos/{objetoId}")
    @Operation(summary = "Asigna un objeto a un mapa, lo busca en base a su ID",description = "POST")
    public ResponseEntity<Void> addObjeto(@PathVariable Long id, @PathVariable Long objetoId) {
        mapaService.addObjeto(id, objetoId);
        return ResponseEntity.ok().build();
    }
}
