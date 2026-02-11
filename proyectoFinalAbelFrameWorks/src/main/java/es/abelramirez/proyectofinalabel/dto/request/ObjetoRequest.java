package es.abelramirez.proyectofinalabel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Objeto}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjetoRequest implements Serializable {
    String nombre;
    String descripcion;
    CategoriaDto categoria;

    /**
     * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Categoria}
     */
    @Value
    public static class CategoriaDto implements Serializable {
        Long id;
    }
}