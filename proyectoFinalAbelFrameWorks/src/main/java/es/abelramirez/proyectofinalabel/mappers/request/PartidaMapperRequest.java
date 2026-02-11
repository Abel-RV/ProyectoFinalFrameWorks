package es.abelramirez.proyectofinalabel.mappers.request;

import es.abelramirez.proyectofinalabel.dto.request.PartidaRequest;
import es.abelramirez.proyectofinalabel.models.entities.Partida;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)public interface PartidaMapperRequest {
    Partida toEntity(PartidaRequest partidaRequest);

    PartidaRequest toDto(Partida partida);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)Partida partialUpdate(PartidaRequest partidaRequest, @MappingTarget Partida partida);
}