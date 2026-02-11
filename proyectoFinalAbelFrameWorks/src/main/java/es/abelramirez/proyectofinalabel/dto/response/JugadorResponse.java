package es.abelramirez.proyectofinalabel.dto.response;

import es.abelramirez.proyectofinalabel.models.enums.EstadoJugador;
import es.abelramirez.proyectofinalabel.models.enums.TipoJuego;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Jugador}
 */
@Value
public class JugadorResponse implements Serializable {
    Long id;
    String nombre;
    String email;
    List<PartidaDto> partidas;

    /**
     * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Partida}
     */
    @Value
    public static class PartidaDto implements Serializable {
        Long id;
        TipoJuego tipoJuego;
        EstadoJugador estadoJugador;
        LocalDateTime fechaPartida;
    }
}