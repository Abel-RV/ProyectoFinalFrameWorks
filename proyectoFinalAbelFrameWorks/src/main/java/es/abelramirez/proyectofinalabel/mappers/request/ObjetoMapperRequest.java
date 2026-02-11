package es.abelramirez.proyectofinalabel.mappers.request;

import es.abelramirez.proyectofinalabel.dto.request.ObjetoRequest;
import es.abelramirez.proyectofinalabel.models.entities.Objeto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ObjetoMapperRequest {
    Objeto toEntity(ObjetoRequest objetoRequest);

    ObjetoRequest toDto(Objeto objeto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Objeto partialUpdate(ObjetoRequest objetoRequest, @MappingTarget Objeto objeto);
}