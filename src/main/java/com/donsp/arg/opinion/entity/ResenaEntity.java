package com.donsp.arg.opinion.entity;

import com.donsp.arg.alojamiento.entity.AlojamientoEntity;
import com.donsp.arg.usuario.entity.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;


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

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "alojamiento_id")
    private AlojamientoEntity alojamiento;
}
