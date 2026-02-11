package es.abelramirez.proyectofinalabel.mappers.response;

import es.abelramirez.proyectofinalabel.dto.response.PersonajeResponse;
import es.abelramirez.proyectofinalabel.models.entities.Personaje;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonajeMapperResponse {
    Personaje toEntity(PersonajeResponse personajeResponse);

    PersonajeResponse toDto(Personaje personaje);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Personaje partialUpdate(PersonajeResponse personajeResponse, @MappingTarget Personaje personaje);
}