package es.abelramirez.proyectofinalabel.repositories;

import es.abelramirez.proyectofinalabel.models.entities.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    Optional<Personaje> findByNombre(String importado);
}