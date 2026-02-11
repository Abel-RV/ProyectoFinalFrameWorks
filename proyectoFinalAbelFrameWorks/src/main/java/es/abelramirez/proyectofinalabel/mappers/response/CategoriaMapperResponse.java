package es.abelramirez.proyectofinalabel.mappers.response;

import es.abelramirez.proyectofinalabel.dto.response.CategoriaResponse;
import es.abelramirez.proyectofinalabel.dto.response.PersonajeResponse;
import es.abelramirez.proyectofinalabel.models.entities.Categoria;
import es.abelramirez.proyectofinalabel.models.entities.Personaje;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaMapperResponse {
    Categoria toEntity(CategoriaResponse categoriaResponse);



    CategoriaResponse toDto(Categoria categoria);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Categoria partialUpdate(CategoriaResponse categoriaResponse, @MappingTarget Categoria categoria);

    @Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
    interface PersonajeMapperResponse {
        Personaje toEntity(PersonajeResponse personajeResponse);

        PersonajeResponse toDto(Personaje personaje);

        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        Personaje partialUpdate(PersonajeResponse personajeResponse, @MappingTarget Personaje personaje);
    }
}