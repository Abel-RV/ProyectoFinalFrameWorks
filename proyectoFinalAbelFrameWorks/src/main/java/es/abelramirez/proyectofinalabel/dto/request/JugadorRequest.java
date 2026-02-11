package es.abelramirez.proyectofinalabel.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Jugador}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JugadorRequest implements Serializable {
    @NotNull(message = "Ingrese el nombre del jugador")
    String nombre;
    @NotNull(message = "Ingrese el email del jugador")
    @Email
    String email;
}