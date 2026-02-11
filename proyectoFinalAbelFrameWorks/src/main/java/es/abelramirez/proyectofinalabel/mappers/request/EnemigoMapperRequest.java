package es.abelramirez.proyectofinalabel.mappers.request;

import es.abelramirez.proyectofinalabel.dto.request.EnemigoRequest;
import es.abelramirez.proyectofinalabel.models.entities.Enemigo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnemigoMapperRequest {
    Enemigo toEntity(EnemigoRequest enemigoRequest);

    EnemigoRequest toDto(Enemigo enemigo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Enemigo partialUpdate(EnemigoRequest enemigoRequest, @MappingTarget Enemigo enemigo);
}