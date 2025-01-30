package com.donsp.arg.reserva.dto;

import com.donsp.arg.alojamiento.entity.AlojamientoEntity;
import com.donsp.arg.usuario.entity.User;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ReservaDTO (LocalDate fechaInicio,
        LocalDate fechaFin,
        Double total,
        User usuario,
        AlojamientoEntity alojamiento){
}
