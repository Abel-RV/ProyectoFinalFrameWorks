package es.abelramirez.proyectofinalabel.dto.response;

import es.abelramirez.proyectofinalabel.models.enums.TipoEnemigo;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Enemigo}
 */
@Value
public class EnemigoResponse implements Serializable {
    Long id;
    String nombreEnemigo;
    Long vida;
    TipoEnemigo tipo;
}