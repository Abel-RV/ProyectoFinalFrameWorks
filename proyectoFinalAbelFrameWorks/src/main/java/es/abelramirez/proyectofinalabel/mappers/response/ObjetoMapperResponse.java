package es.abelramirez.proyectofinalabel.mappers.response;

import es.abelramirez.proyectofinalabel.dto.response.ObjetoResponse;
import es.abelramirez.proyectofinalabel.models.entities.Objeto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ObjetoMapperResponse {
    Objeto toEntity(ObjetoResponse objetoResponse);

    ObjetoResponse toDto(Objeto objeto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Objeto partialUpdate(ObjetoResponse objetoResponse, @MappingTarget Objeto objeto);
}