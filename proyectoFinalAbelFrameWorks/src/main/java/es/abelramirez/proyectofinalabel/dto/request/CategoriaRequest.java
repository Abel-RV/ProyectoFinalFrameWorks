package es.abelramirez.proyectofinalabel.dto.request;

import jakarta.validation.constraints.NotNull;
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
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequest implements Serializable {
    @NotNull(message = "Ingrese el nombre de la categoria")
    String nombreCategoria;
}