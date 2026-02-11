package es.abelramirez.proyectofinalabel.dto.request;

import es.abelramirez.proyectofinalabel.models.enums.TipoSala;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Mapa}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapaRequest implements Serializable {
    @NotNull(message = "Ingrese el nombre del mapa")
    String nombre;
    @NotNull
    TipoSala tipoSala;
}