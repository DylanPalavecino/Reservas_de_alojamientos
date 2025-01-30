package com.donsp.arg.alojamiento.entity;

import com.donsp.arg.alojamiento.models.TipoAlojamiento;
import com.donsp.arg.reserva.entity.ReservaEntity;
import com.donsp.arg.usuario.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "alojamiento")
public class AlojamientoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String direccion;
    private TipoAlojamiento tipoAlojamiento;
    private Double precioPorNoche;
    private Integer capacidadPersonas;
    @ManyToOne @JoinColumn(name = "dueño_id")
    private User propietario;
    @OneToMany(mappedBy = "alojamiento", cascade = CascadeType.ALL)
    private List<ReservaEntity> reservas;


}
