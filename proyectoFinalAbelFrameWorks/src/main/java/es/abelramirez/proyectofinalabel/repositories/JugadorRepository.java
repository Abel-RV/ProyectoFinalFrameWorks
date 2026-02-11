package es.abelramirez.proyectofinalabel.repositories;

import es.abelramirez.proyectofinalabel.models.entities.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    Optional<Jugador> findByNombre(String importado);
    Optional<Jugador> findByEmail(String email);
}