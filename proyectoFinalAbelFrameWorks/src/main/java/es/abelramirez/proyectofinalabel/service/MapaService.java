package es.abelramirez.proyectofinalabel.service;

import es.abelramirez.proyectofinalabel.dto.request.MapaRequest;
import es.abelramirez.proyectofinalabel.dto.response.MapaResponse;
import es.abelramirez.proyectofinalabel.mappers.request.MapaMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.MapaMapperResponse;
import es.abelramirez.proyectofinalabel.models.entities.Mapa;
import es.abelramirez.proyectofinalabel.models.entities.Objeto;
import es.abelramirez.proyectofinalabel.repositories.MapaRepository;
import es.abelramirez.proyectofinalabel.repositories.ObjetoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapaService {
    private final MapaRepository mapaRepository;
    private final ObjetoRepository objetoRepository;
    private final MapaMapperRequest mapaMapperRequest;
    private final MapaMapperResponse mapaMapperResponse;

    @Transactional
    public void addObjeto(Long mapaId, Long objetoId) {
        Mapa mapa = mapaRepository.findById(mapaId)
                .orElseThrow(() -> new RuntimeException("Mapa no encontrado"));

        Objeto objeto = objetoRepository.findById(objetoId)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado"));
        mapa.getObjetos().add(objeto);
        mapaRepository.save(mapa);
    }

    public List<MapaResponse> findAll(){
        return mapaRepository.findAll().stream().map(mapaMapperResponse::toDto).collect(Collectors.toList());
    }

    public MapaRequest create(MapaRequest request){
        Mapa obj1 = mapaMapperRequest.toEntity(request);
        Mapa objNuevo = mapaRepository.save(obj1);
        return mapaMapperRequest.toDto(objNuevo);
    }

    public MapaResponse findById(Long id){
        return mapaRepository.findById(id).map(mapaMapperResponse::toDto).orElseThrow(()->new RuntimeException("Id no encontrado"));
    }

    public void delete(Long id){
        mapaRepository.deleteById(id);
    }

    public MapaResponse update(Long id, MapaRequest request){
        Mapa obj1 = mapaRepository.findById(id).orElseThrow(()->new RuntimeException("Id no encontrado"));
        mapaMapperRequest.partialUpdate(request,obj1);
        Mapa nuevo = mapaRepository.save(obj1);
        return mapaMapperResponse.toDto(nuevo);
    }
}
