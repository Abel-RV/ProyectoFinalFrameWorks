package es.abelramirez.proyectofinalabel.service;

import es.abelramirez.proyectofinalabel.dto.request.CategoriaRequest;
import es.abelramirez.proyectofinalabel.dto.response.CategoriaResponse;
import es.abelramirez.proyectofinalabel.mappers.request.CategoriaMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.CategoriaMapperResponse;
import es.abelramirez.proyectofinalabel.models.entities.Categoria;
import es.abelramirez.proyectofinalabel.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapperRequest categoriaMapperRequest;
    private final CategoriaMapperResponse categoriaMapperResponse;

    public Page<CategoriaResponse> findAll(Pageable pageable){
        return categoriaRepository.findAll(pageable)
                .map(categoriaMapperResponse::toDto);
    }

    public CategoriaRequest create(CategoriaRequest request){
        Categoria obj1 = categoriaMapperRequest.toEntity(request);
        Categoria objNuevo = categoriaRepository.save(obj1);
        return categoriaMapperRequest.toDto(objNuevo);
    }

    public CategoriaResponse findById(Long id){
        return categoriaRepository.findById(id).map(categoriaMapperResponse::toDto).orElseThrow(()->new RuntimeException("Id no encontrado"));
    }

    public void delete(Long id){
        categoriaRepository.deleteById(id);
    }

    public CategoriaResponse update(Long id, CategoriaRequest request){
        Categoria obj1 = categoriaRepository.findById(id).orElseThrow(()->new RuntimeException("Id no encontrado"));
        categoriaMapperRequest.partialUpdate(request,obj1);
        Categoria nuevo = categoriaRepository.save(obj1);
        return categoriaMapperResponse.toDto(nuevo);
    }
}
