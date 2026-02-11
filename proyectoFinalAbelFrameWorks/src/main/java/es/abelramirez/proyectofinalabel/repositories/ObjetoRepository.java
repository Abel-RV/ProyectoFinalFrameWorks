package es.abelramirez.proyectofinalabel.repositories;

import es.abelramirez.proyectofinalabel.models.entities.Objeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ObjetoRepository extends JpaRepository<Objeto, Long> {
    Optional<Objeto> findByNombre(String importado);
}