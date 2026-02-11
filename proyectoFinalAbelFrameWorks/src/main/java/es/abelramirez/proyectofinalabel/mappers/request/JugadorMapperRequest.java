package es.abelramirez.proyectofinalabel.mappers.request;

import es.abelramirez.proyectofinalabel.dto.request.JugadorRequest;
import es.abelramirez.proyectofinalabel.dto.request.RegisterRequest;
import es.abelramirez.proyectofinalabel.models.entities.Jugador;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface JugadorMapperRequest {
    Jugador toEntity(JugadorRequest jugadorRequest);

    JugadorRequest toDto(Jugador jugador);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Jugador partialUpdate(JugadorRequest jugadorRequest, @MappingTarget Jugador jugador);

    @Mapping(target = "roles", constant = "USER")      // Asigna rol por defecto
    @Mapping(target = "password", ignore = true)       // Ignoramos password para cifrarla en el servicio
    @Mapping(target = "id", ignore = true)             // Se genera automáticamente
    @Mapping(target = "partidas", ignore = true)       // Lista vacía al inicio
    Jugador toEntity(RegisterRequest request);
}