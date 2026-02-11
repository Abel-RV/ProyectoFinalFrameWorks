package es.abelramirez.proyectofinalabel.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Size;

/**
 * DTO for {@link com.abel.ejercicio2.entities.Usuario}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PasswordRequest(
        @Size(min = 3) String password,
        @Size(min = 3) String passwordNueva
) {
}