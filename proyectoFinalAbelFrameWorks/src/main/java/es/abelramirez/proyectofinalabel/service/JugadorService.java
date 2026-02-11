package es.abelramirez.proyectofinalabel.service;

import es.abelramirez.proyectofinalabel.dto.request.JugadorRequest;
import es.abelramirez.proyectofinalabel.dto.response.JugadorResponse;
import es.abelramirez.proyectofinalabel.mappers.request.JugadorMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.JugadorMapperResponse;
import es.abelramirez.proyectofinalabel.models.entities.Jugador;
import es.abelramirez.proyectofinalabel.repositories.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JugadorService {
    private final JugadorRepository jugadorRepository;
    private final JugadorMapperRequest jugadorMapperRequest;
    private final JugadorMapperResponse jugadorMapperResponse;

    public List<JugadorResponse> findAll(){
        return jugadorRepository.findAll().stream().map(jugadorMapperResponse::toDto).collect(Collectors.toList());
    }

    public JugadorRequest create(JugadorRequest request){
        Jugador obj1 = jugadorMapperRequest.toEntity(request);
        Jugador objNuevo = jugadorRepository.save(obj1);
        return jugadorMapperRequest.toDto(objNuevo);
    }

    public JugadorResponse findById(Long id){
        return jugadorRepository.findById(id).map(jugadorMapperResponse::toDto).orElseThrow(()->new RuntimeException("Id no encontrado"));
    }

    public void delete(Long id){
        jugadorRepository.deleteById(id);
    }

    public JugadorResponse update(Long id, JugadorRequest request){
        Jugador obj1 = jugadorRepository.findById(id).orElseThrow(()->new RuntimeException("Id no encontrado"));
        jugadorMapperRequest.partialUpdate(request,obj1);
        Jugador nuevo = jugadorRepository.save(obj1);
        return jugadorMapperResponse.toDto(nuevo);
    }
}
