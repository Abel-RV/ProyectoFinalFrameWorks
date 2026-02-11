package es.abelramirez.proyectofinalabel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Categoria}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponse implements Serializable {
    Long id;
    String nombreCategoria;
    List<ObjetoDto> objetos;

    /**
     * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Objeto}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ObjetoDto implements Serializable {
        Long id;
        String nombre;
        String descripcion;
    }
}