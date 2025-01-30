package com.donsp.arg.opinion.entity;

import com.donsp.arg.alojamiento.entity.AlojamientoEntity;
import com.donsp.arg.usuario.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "resena")
public class ResenaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Byte calificacion;
    private String comentario;
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "alojamiento_id")
    private AlojamientoEntity alojamiento;
}
