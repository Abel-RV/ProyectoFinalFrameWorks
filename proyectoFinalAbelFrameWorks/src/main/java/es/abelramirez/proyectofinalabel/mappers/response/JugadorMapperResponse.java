package es.abelramirez.proyectofinalabel.mappers.response;

import es.abelramirez.proyectofinalabel.dto.response.JugadorResponse;
import es.abelramirez.proyectofinalabel.models.entities.Jugador;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface JugadorMapperResponse {
    Jugador toEntity(JugadorResponse jugadorResponse);

    JugadorResponse toDto(Jugador jugador);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Jugador partialUpdate(JugadorResponse jugadorResponse, @MappingTarget Jugador jugador);
}