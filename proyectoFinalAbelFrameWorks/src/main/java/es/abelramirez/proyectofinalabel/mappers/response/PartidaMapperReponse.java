package es.abelramirez.proyectofinalabel.mappers.response;

import es.abelramirez.proyectofinalabel.dto.response.PartidaResponse;
import es.abelramirez.proyectofinalabel.models.entities.Partida;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PartidaMapperReponse {
    Partida toEntity(PartidaResponse partidaResponse);

    PartidaResponse toDto(Partida partida);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Partida partialUpdate(PartidaResponse partidaResponse, @MappingTarget Partida partida);
}