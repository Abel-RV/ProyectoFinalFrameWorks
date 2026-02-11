package es.abelramirez.proyectofinalabel.dto.request;

import es.abelramirez.proyectofinalabel.models.enums.EstadoJugador;
import es.abelramirez.proyectofinalabel.models.enums.TipoJuego;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Partida}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidaRequest implements Serializable {
    TipoJuego tipoJuego;
    EstadoJugador estadoJugador;
    JugadorDto jugador;
    PersonajeDto personaje;

    /**
     * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Jugador}
     */
    @Value
    public static class JugadorDto implements Serializable {
        Long id;
    }

    /**
     * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Personaje}
     */
    @Value
    public static class PersonajeDto implements Serializable {
        Long id;
    }


}