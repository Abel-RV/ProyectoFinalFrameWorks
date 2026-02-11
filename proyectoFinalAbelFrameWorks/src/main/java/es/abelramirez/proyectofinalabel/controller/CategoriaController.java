package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.CategoriaRequest;
import es.abelramirez.proyectofinalabel.dto.request.ObjetoRequest;
import es.abelramirez.proyectofinalabel.dto.response.CategoriaResponse;
import es.abelramirez.proyectofinalabel.dto.response.ObjetoResponse;
import es.abelramirez.proyectofinalabel.models.entities.Categoria;
import es.abelramirez.proyectofinalabel.repositories.CategoriaRepository;
import es.abelramirez.proyectofinalabel.service.CategoriaService;
import es.abelramirez.proyectofinalabel.service.ObjetoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
@Tag(name="Controlador de las categorías",description = "Gestiona las categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @Operation(summary = "Obtiene todas las categorias",description = "GET")
    @GetMapping
    public ResponseEntity<Page<CategoriaResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(categoriaService.findAll(pageable));
    }

    @Operation(summary = "Obtiene la categoria especifica en base a su ID",description = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> getById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @Operation(summary = "Crea una categoria nueva",description = "POST")
    @PostMapping
    public ResponseEntity<CategoriaRequest> post(@Valid @RequestBody CategoriaRequest categoriaRequest) {
        return ResponseEntity.ok(categoriaService.create(categoriaRequest));
    }

    @Operation(summary = "Actualiza una categoria ya existente, la busca mediante su ID",description = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> put(@Valid @PathVariable Long id,@RequestBody CategoriaRequest categoriaRequest) {
        return ResponseEntity.ok(categoriaService.update(id,categoriaRequest));
    }
    @Operation(summary = "Borra una categoria, la busca mediante su ID",description = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
