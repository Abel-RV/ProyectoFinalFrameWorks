package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.ObjetoRequest;
import es.abelramirez.proyectofinalabel.dto.response.ObjetoResponse;
import es.abelramirez.proyectofinalabel.service.ObjetoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/objetos")
@RequiredArgsConstructor
@Tag(name="Controlador de los objetos",description = "Gestiona los objetos")
public class ObjetoController {
    private final ObjetoService objetoService;

    @Operation(summary = "Obtiene todos los objetos",description = "GET")
    @GetMapping
    public ResponseEntity<Page<ObjetoResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(objetoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un Objeto especifico en base a su ID",description = "GET")
    public ResponseEntity<ObjetoResponse> getById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(objetoService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo objeto",description = "POST")
    public ResponseEntity<ObjetoRequest> post(@RequestBody ObjetoRequest categoriaRequest) {
        return ResponseEntity.ok(objetoService.create(categoriaRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita un objeto ya existente, lo busca en base a su ID",description = "PUT")
    public ResponseEntity<ObjetoResponse> put(@Valid @PathVariable Long id,@RequestBody ObjetoRequest categoriaRequest) {
        return ResponseEntity.ok(objetoService.update(id,categoriaRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un objeto ya existente, lo busca en base a su ID",description = "DELETE")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        objetoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
