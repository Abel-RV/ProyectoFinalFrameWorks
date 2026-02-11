package es.abelramirez.proyectofinalabel.service;

import es.abelramirez.proyectofinalabel.dto.request.PartidaRequest;
import es.abelramirez.proyectofinalabel.dto.response.PartidaResponse;
import es.abelramirez.proyectofinalabel.mappers.request.PartidaMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.PartidaMapperReponse;
import es.abelramirez.proyectofinalabel.models.entities.*;
import es.abelramirez.proyectofinalabel.models.enums.EstadoJugador;
import es.abelramirez.proyectofinalabel.models.enums.TipoJuego;
import es.abelramirez.proyectofinalabel.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartidaService {
    private final PartidaRepository partidaRepository;
    private final ObjetoRepository objetoRepository;
    private final EnemigoRepository enemigoRepository;
    private final PersonajeRepository personajeRepository;
    private final JugadorRepository  jugadorRepository;
    private final PartidaMapperRequest partidaMapperRequest;
    private final PartidaMapperReponse partidaMapperReponse;

    @Transactional
    public void anadirObjeto(Long idPartida, Long idObjeto) {
        Partida partida = partidaRepository.findById(idPartida)
                .orElseThrow(() -> new RuntimeException("Partida no encontrada"));
        Objeto objeto = objetoRepository.findById(idObjeto)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado"));

        partida.getObjetos().add(objeto);
        partidaRepository.save(partida);
    }

    @Transactional
    public void anadirEnemigo(Long idPartida, Long idEnemigo) {
        Partida partida = partidaRepository.findById(idPartida)
                .orElseThrow(() -> new RuntimeException("Partida no encontrada"));
        Enemigo enemigo = enemigoRepository.findById(idEnemigo)
                .orElseThrow(() -> new RuntimeException("Enemigo no encontrado"));

        partida.getEnemigos().add(enemigo);
        partidaRepository.save(partida);
    }

    public List<PartidaResponse> findAll(){
        return partidaRepository.findAll().stream().map(partidaMapperReponse::toDto).collect(Collectors.toList());
    }

    public PartidaRequest create(PartidaRequest request){
        Partida obj1 = partidaMapperRequest.toEntity(request);

        Personaje personaje = personajeRepository.findById(request.getPersonaje().getId())
                .orElseThrow(() -> new RuntimeException("Personaje no encontrado"));

        Jugador jugador = jugadorRepository.findById(request.getJugador().getId())
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        obj1.setPersonaje(personaje);
        obj1.setJugador(jugador);

        if(personaje.getNumCorazones() < 1){
            throw new RuntimeException("No se pueden tener 0 corazones");
        }

        Partida objNuevo = partidaRepository.save(obj1);
        return partidaMapperRequest.toDto(objNuevo);
    }


    public Page<PartidaResponse> obtenerEstadoTipo(TipoJuego tipoJuego, EstadoJugador estadoJugador, Pageable pageable) {
        return partidaRepository.findByTipoJuegoAndEstadoJugador(tipoJuego,estadoJugador,pageable)
                .map(partidaMapperReponse::toDto);
    }

    public PartidaResponse findById(Long id){
        return partidaRepository.findById(id).map(partidaMapperReponse::toDto).orElseThrow();
    }

    public void delete(Long id){
        partidaRepository.deleteById(id);
    }

    public PartidaResponse update(Long id, PartidaRequest request){
        Partida obj1 = partidaRepository.findById(id).orElseThrow(()->new RuntimeException("Id no encontrado"));
        partidaMapperRequest.partialUpdate(request,obj1);
        Partida nuevo = partidaRepository.save(obj1);
        return partidaMapperReponse.toDto(nuevo);
    }

}
