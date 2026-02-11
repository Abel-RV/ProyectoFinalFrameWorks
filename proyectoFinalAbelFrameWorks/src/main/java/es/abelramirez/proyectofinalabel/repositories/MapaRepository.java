package es.abelramirez.proyectofinalabel.repositories;

import es.abelramirez.proyectofinalabel.models.entities.Mapa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MapaRepository extends JpaRepository<Mapa, Long> {
    Optional<Mapa> findByNombre(String importado);
}