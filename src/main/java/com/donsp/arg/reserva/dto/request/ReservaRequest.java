package com.donsp.arg.reserva.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ReservaRequest (Long userId, Long alojamientoId, LocalDate fechaInicio, LocalDate fechaFin){
}
