package es.abelramirez.proyectofinalabel.repositories;

import es.abelramirez.proyectofinalabel.models.entities.Enemigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnemigoRepository extends JpaRepository<Enemigo, Long> {
    Optional<Enemigo> findByNombreEnemigo(String importado);
}