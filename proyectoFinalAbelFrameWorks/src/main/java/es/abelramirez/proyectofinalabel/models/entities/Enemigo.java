package es.abelramirez.proyectofinalabel.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.abelramirez.proyectofinalabel.models.enums.TipoEnemigo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "enemigo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enemigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombreEnemigo;

    private Long vida;

    @Enumerated(EnumType.STRING)
    private TipoEnemigo tipo;

    @ManyToMany(mappedBy = "enemigos")
    @JsonIgnore
    private List<Partida> partidas = new ArrayList<>();

}