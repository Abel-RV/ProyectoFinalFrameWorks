package es.abelramirez.proyectofinalabel.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.abelramirez.proyectofinalabel.models.enums.TipoSala;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name = "mapa")
@AllArgsConstructor
@NoArgsConstructor
public class Mapa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoSala tipoSala;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "mapa_objetos",
            joinColumns = @JoinColumn(name = "mapa_id"),
            inverseJoinColumns = @JoinColumn(name = "objetos_id"))
    private List<Objeto> objetos = new ArrayList<>();

}