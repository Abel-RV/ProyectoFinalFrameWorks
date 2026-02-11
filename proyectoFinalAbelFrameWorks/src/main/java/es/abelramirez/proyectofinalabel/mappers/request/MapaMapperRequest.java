package es.abelramirez.proyectofinalabel.mappers.request;

import es.abelramirez.proyectofinalabel.dto.request.MapaRequest;
import es.abelramirez.proyectofinalabel.models.entities.Mapa;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MapaMapperRequest {
    Mapa toEntity(MapaRequest mapaRequest);

    MapaRequest toDto(Mapa mapa);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Mapa partialUpdate(MapaRequest mapaRequest, @MappingTarget Mapa mapa);
}