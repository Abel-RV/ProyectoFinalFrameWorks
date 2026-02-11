package es.abelramirez.proyectofinalabel.mappers.response;

import es.abelramirez.proyectofinalabel.dto.response.EnemigoResponse;
import es.abelramirez.proyectofinalabel.models.entities.Enemigo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnemigoMapperResponse {
    Enemigo toEntity(EnemigoResponse enemigoResponse);

    EnemigoResponse toDto(Enemigo enemigo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Enemigo partialUpdate(EnemigoResponse enemigoResponse, @MappingTarget Enemigo enemigo);
}