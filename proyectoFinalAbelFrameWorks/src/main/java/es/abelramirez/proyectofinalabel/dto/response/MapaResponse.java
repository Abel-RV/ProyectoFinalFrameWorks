package es.abelramirez.proyectofinalabel.dto.response;

import es.abelramirez.proyectofinalabel.models.enums.TipoSala;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Mapa}
 */
@Value
public class MapaResponse implements Serializable {
    Long id;
    String nombre;
    TipoSala tipoSala;
}