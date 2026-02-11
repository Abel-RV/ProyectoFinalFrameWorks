package es.abelramirez.proyectofinalabel.service;

import es.abelramirez.proyectofinalabel.dto.request.PersonajeRequest;
import es.abelramirez.proyectofinalabel.dto.response.PersonajeResponse;
import es.abelramirez.proyectofinalabel.mappers.request.PersonajeMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.PersonajeMapperResponse;
import es.abelramirez.proyectofinalabel.models.entities.Personaje;
import es.abelramirez.proyectofinalabel.repositories.PersonajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonajeService {
    private final PersonajeRepository personajeRepository;
    private final PersonajeMapperRequest personajeMapperRequest;
    private final PersonajeMapperResponse personajeMapperResponse;

    public List<PersonajeResponse> findAll(){
        return personajeRepository.findAll().stream().map(personajeMapperResponse::toDto).collect(Collectors.toList());
    }

    public PersonajeRequest create(PersonajeRequest request){
        Personaje obj1 = personajeMapperRequest.toEntity(request);
        Personaje objNuevo = personajeRepository.save(obj1);
        return personajeMapperRequest.toDto(objNuevo);
    }

    public PersonajeResponse findById(Long id){
        return personajeRepository.findById(id).map(personajeMapperResponse::toDto).orElseThrow();
    }

    public void delete(Long id){
        personajeRepository.deleteById(id);
    }

    public PersonajeResponse update(Long id, PersonajeRequest request){
        Personaje obj1 = personajeRepository.findById(id).orElseThrow(()->new RuntimeException("Id no encontrado"));
        personajeMapperRequest.partialUpdate(request,obj1);
        Personaje nuevo = personajeRepository.save(obj1);
        return personajeMapperResponse.toDto(nuevo);
    }

}
