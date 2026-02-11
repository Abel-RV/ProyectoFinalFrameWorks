package es.abelramirez.proyectofinalabel.dto.request;

import es.abelramirez.proyectofinalabel.models.enums.TipoCorazon;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Personaje}
 */
@Value
public class PersonajeRequest implements Serializable {
    @NotNull(message = "Nombre obligatorio")
    @NotEmpty(message = "No puedes poner un nombre vacio")
    String nombre;
    @NotNull(message = "Numero de corazones obligatorio")
    @Min(message = "Minimo un corazón", value = 1)
    @Max(message = "El maximo son 12 corazones", value = 12)
    Integer numCorazones;
    @NotNull(message = "Tiene que ingresar el tipo de corazón del personaje")
    TipoCorazon tipoCorazon;
    @NotNull(message = "Tiene que ingresar el ataque del personaje")
    @Min(message = "Minimo 1 de ataque", value = 1)
    @Max(message = "Maximo 99 de ataque", value = 99)
    Double ataque;
    @NotNull(message = "Obligatorio indicar velocidad")
    @Min(message = "Minimo 1 de velocidad", value = 1)
    @Max(message = "Maximo 99 de velocidad", value = 99)
    Double velocidad;
    @NotNull(message = "Obligatorio indicar la velocidad de lagrimas")
    Double velocidadLagrimas;
    Double alcance;
    Double rango;
    Double suerte;
}