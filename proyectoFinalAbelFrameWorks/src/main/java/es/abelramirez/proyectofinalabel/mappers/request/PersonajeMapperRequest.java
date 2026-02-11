package es.abelramirez.proyectofinalabel.mappers.request;

import es.abelramirez.proyectofinalabel.dto.request.PersonajeRequest;
import es.abelramirez.proyectofinalabel.models.entities.Personaje;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonajeMapperRequest {
    Personaje toEntity(PersonajeRequest personajeRequest);

    PersonajeRequest toDto(Personaje personaje);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Personaje partialUpdate(PersonajeRequest personajeRequest, @MappingTarget Personaje personaje);

}