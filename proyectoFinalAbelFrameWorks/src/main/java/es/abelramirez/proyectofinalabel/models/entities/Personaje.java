package es.abelramirez.proyectofinalabel.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.abelramirez.proyectofinalabel.models.enums.TipoCorazon;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "personaje")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;
    private Integer numCorazones;

    @Enumerated(EnumType.STRING)
    private TipoCorazon tipoCorazon=TipoCorazon.CORAZON_ROJO;

    private Double ataque;
    private Double velocidad;
    private Double velocidadLagrimas;
    private Double alcance;
    private Double rango;
    private Double suerte;

    @OneToMany(mappedBy = "personaje", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Partida> partidas = new ArrayList<>();

}