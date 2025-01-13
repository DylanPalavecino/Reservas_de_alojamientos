package com.donsp.arg.reserva.dto;

import com.donsp.arg.alojamiento.entity.AlojamientoEntity;
import com.donsp.arg.usuario.entity.UsuarioEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Builder
public record ReservaDTO (LocalDate fechaInicio,
        LocalDate fechaFin,
        Double total,
        UsuarioEntity usuario,
        AlojamientoEntity alojamiento){
}
