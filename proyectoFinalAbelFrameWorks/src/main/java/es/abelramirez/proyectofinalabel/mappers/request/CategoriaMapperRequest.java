package es.abelramirez.proyectofinalabel.mappers.request;

import es.abelramirez.proyectofinalabel.dto.request.CategoriaRequest;
import es.abelramirez.proyectofinalabel.models.entities.Categoria;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaMapperRequest {
    Categoria toEntity(CategoriaRequest categoriaRequest);



    CategoriaRequest toDto(Categoria categoria);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Categoria partialUpdate(CategoriaRequest categoriaRequest, @MappingTarget Categoria categoria);
}