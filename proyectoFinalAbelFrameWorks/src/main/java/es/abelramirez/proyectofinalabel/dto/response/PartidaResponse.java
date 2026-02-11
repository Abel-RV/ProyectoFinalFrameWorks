package es.abelramirez.proyectofinalabel.dto.response;

import es.abelramirez.proyectofinalabel.models.enums.EstadoJugador;
import es.abelramirez.proyectofinalabel.models.enums.TipoJuego;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Partida}
 */
@Value
public class PartidaResponse implements Serializable {
    Long id;
    TipoJuego tipoJuego;
    EstadoJugador estadoJugador;
    LocalDateTime fechaPartida;
    JugadorDto jugador;
    PersonajeDto personaje;
    List<EnemigoDto> enemigos;
    List<ObjetoDto> objetos;

    /**
     * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Jugador}
     */
    @Value
    public static class JugadorDto implements Serializable {
        Long id;
        String nombre;
    }

    /**
     * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Personaje}
     */
    @Value
    public static class PersonajeDto implements Serializable {
        Long id;
        String nombre;
    }

    /**
     * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Enemigo}
     */
    @Value
    public static class EnemigoDto implements Serializable {
        Long id;
        String nombreEnemigo;

    }

    /**
     * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Objeto}
     */
    @Value
    public static class ObjetoDto implements Serializable {
        Long id;
        String nombre;

    }
}