package es.abelramirez.proyectofinalabel.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Builder
@Getter
@Setter
@Entity
@Table(name = "objeto")
@NoArgsConstructor
@AllArgsConstructor
public class Objeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToMany(mappedBy = "objetos")
    @JsonIgnore
    private List<Partida> partidas = new ArrayList<>();

    @ManyToMany(mappedBy = "objetos")
    @JsonIgnore
    private List<Mapa> mapas = new ArrayList<>();
}