package es.abelramirez.proyectofinalabel.service;

import es.abelramirez.proyectofinalabel.dto.request.EnemigoRequest;
import es.abelramirez.proyectofinalabel.dto.response.EnemigoResponse;
import es.abelramirez.proyectofinalabel.models.entities.Enemigo;
import es.abelramirez.proyectofinalabel.mappers.request.EnemigoMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.EnemigoMapperResponse;
import es.abelramirez.proyectofinalabel.repositories.EnemigoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnemigoService {
    private final EnemigoRepository enemigoRepository;
    private final EnemigoMapperRequest enemigoMapperRequest;
    private final EnemigoMapperResponse enemigoMapperResponse;

    public List<EnemigoResponse> findAll(){
        return enemigoRepository.findAll().stream().map(enemigoMapperResponse::toDto).collect(Collectors.toList());
    }

    public EnemigoRequest create(EnemigoRequest request){
        Enemigo obj1 = enemigoMapperRequest.toEntity(request);
        Enemigo objNuevo = enemigoRepository.save(obj1);
        return enemigoMapperRequest.toDto(objNuevo);
    }

    public EnemigoResponse findById(Long id){
        return enemigoRepository.findById(id).map(enemigoMapperResponse::toDto).orElseThrow(()->new RuntimeException("Id no encontrado"));
    }

    public void delete(Long id){
        enemigoRepository.deleteById(id);
    }

    public EnemigoResponse update(Long id, EnemigoRequest request){
        Enemigo obj1 = enemigoRepository.findById(id).orElseThrow(()->new RuntimeException("Id no encontrado"));
        enemigoMapperRequest.partialUpdate(request,obj1);
        Enemigo nuevo = enemigoRepository.save(obj1);
        return enemigoMapperResponse.toDto(nuevo);
    }

}
