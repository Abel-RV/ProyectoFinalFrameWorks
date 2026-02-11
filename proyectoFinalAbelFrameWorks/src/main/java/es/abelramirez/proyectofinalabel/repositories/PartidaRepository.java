package es.abelramirez.proyectofinalabel.repositories;

import es.abelramirez.proyectofinalabel.models.entities.Partida;
import es.abelramirez.proyectofinalabel.models.enums.EstadoJugador;
import es.abelramirez.proyectofinalabel.models.enums.TipoJuego;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    Page<Partida> findByTipoJuegoAndEstadoJugador(TipoJuego tipoJuego,EstadoJugador estadoJugador, Pageable pageable);
}