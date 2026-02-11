package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.PartidaRequest;
import es.abelramirez.proyectofinalabel.dto.response.PartidaResponse;
import es.abelramirez.proyectofinalabel.models.enums.EstadoJugador;
import es.abelramirez.proyectofinalabel.models.enums.TipoJuego;
import es.abelramirez.proyectofinalabel.service.PartidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partidas")
@RequiredArgsConstructor
@Tag(name="Controlador de las partidas",description = "Gestiona las partidas")
public class PartidaController {
    private final PartidaService partidaService;

    @GetMapping
    @Operation(summary = "Obtiene todas las partidas",description = "GET")
    public ResponseEntity<List<PartidaResponse>> getAll() {
        return ResponseEntity.ok(partidaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un mapa especifico en base a su ID",description = "GET")
    public ResponseEntity<PartidaResponse> getById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(partidaService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crea una nueva partida",description = "POST")
    public ResponseEntity<PartidaRequest> post(@RequestBody PartidaRequest categoriaRequest) {
        return ResponseEntity.ok(partidaService.create(categoriaRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita una partida ya existente, la busca en base a su ID",description = "PUT")
    public ResponseEntity<PartidaResponse> put(@Valid @PathVariable Long id,@RequestBody PartidaRequest categoriaRequest) {
        return ResponseEntity.ok(partidaService.update(id,categoriaRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borra una partida ya existente, la busca en base a su ID",description = "DELETE")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        partidaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Asigna un objeto a una partida especifica",description = "POST")
    @PostMapping("/{id}/objetos/{idObjeto}")
    public ResponseEntity<Void> addObjeto(@PathVariable Long id, @PathVariable Long idObjeto) {

        partidaService.anadirObjeto(id, idObjeto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/enemigos/{idEnemigo}")
    @Operation(summary = "Asigna un enemigo a una partida especifica",description = "POST")
    public ResponseEntity<Void> addEnemigo(@PathVariable Long id, @PathVariable Long idEnemigo) {
        partidaService.anadirEnemigo(id, idEnemigo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/busqueda")
    public ResponseEntity<Page<PartidaResponse>> get(@RequestParam TipoJuego tipoJuego, @RequestParam EstadoJugador estadoJugador, Pageable pageable) {
        return ResponseEntity.ok(partidaService.obtenerEstadoTipo(tipoJuego, estadoJugador, pageable));
        
    }
}
