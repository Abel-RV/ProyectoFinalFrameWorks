package es.abelramirez.proyectofinalabel.mappers.response;

import es.abelramirez.proyectofinalabel.dto.response.MapaResponse;
import es.abelramirez.proyectofinalabel.models.entities.Mapa;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MapaMapperResponse {
    Mapa toEntity(MapaResponse mapaResponse);

    MapaResponse toDto(Mapa mapa);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Mapa partialUpdate(MapaResponse mapaResponse, @MappingTarget Mapa mapa);
}