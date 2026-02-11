package es.abelramirez.proyectofinalabel.dto.request;

import es.abelramirez.proyectofinalabel.models.enums.TipoEnemigo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Enemigo}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnemigoRequest implements Serializable {
    @NotNull(message = "Ingrese el nombre del enemigo")
    String nombreEnemigo;
    @NotNull(message = "Ingrese la vida del enemigo")
    @Min(10) @Max(999)
    Long vida;
    @NotNull(message = "Ingrese el tipo de enemigo")
    TipoEnemigo tipo;
}