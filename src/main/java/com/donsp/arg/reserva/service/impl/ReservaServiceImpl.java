package com.donsp.arg.reserva.service.impl;

import com.donsp.arg.alojamiento.service.IAlojamientoService;
import com.donsp.arg.reserva.dto.ReservaDTO;
import com.donsp.arg.reserva.dto.request.ReservaRequest;
import com.donsp.arg.reserva.entity.ReservaEntity;
import com.donsp.arg.reserva.repository.IReservaRepository;
import com.donsp.arg.reserva.service.IReservaService;
import com.donsp.arg.usuario.entity.User;
import com.donsp.arg.usuario.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservaServiceImpl implements IReservaService {

    private final IUserService usuarioService;
    private final IAlojamientoService alojamientoService;
    private final IReservaRepository reservaRepository;

    @Override
    public ReservaDTO generarReserva(ReservaRequest reservaRequest) throws Exception {

        boolean disponible = validarDisponibilidad(
                reservaRequest.alojamientoId(),
                reservaRequest.fechaInicio(),
                reservaRequest.fechaFin());

        if(!disponible){
            throw new Exception("Reserva no disponible"); //cambiar esto
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User usuario = usuarioService.findUserByUsername(username);

        double noches = ChronoUnit.DAYS.between(reservaRequest.fechaInicio(), reservaRequest.fechaFin());

        double precioTotal = noches * ((alojamientoService.buscarEntityPorId(reservaRequest.alojamientoId())).getPrecioPorNoche());

        ReservaEntity reservaEntity = ReservaEntity.builder()
                .alojamiento(alojamientoService.buscarEntityPorId(reservaRequest.alojamientoId()))
                .usuario(usuario) //Cambiar esto
                .fechaInicio(reservaRequest.fechaInicio())
                .fechaFin(reservaRequest.fechaFin())
                .total(precioTotal)
                .build();
        reservaRepository.save(reservaEntity);
        return ReservaDTO.builder()
                .alojamiento(alojamientoService.buscarEntityPorId(reservaRequest.alojamientoId()))
                .usuario(usuario)
                .total(precioTotal)
                .fechaInicio(reservaRequest.fechaInicio())
                .fechaFin(reservaRequest.fechaFin())
                .build();
    }

    @Override
    public String eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
        return "Reserva eliminada";
    }

    @Override
    public List<ReservaDTO> mostrarReservas() throws Exception {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User usuario = usuarioService.findUserByUsername(username);

        if (usuario == null || usuario.getReservas().isEmpty() ) {
            throw new Exception("No tienes reservas"); // CAMBIAR EXCEPTION
        }

        List<ReservaEntity> reservas = reservaRepository.findByUsuario(usuario);
        return reservas.stream()
                .map(reserva -> ReservaDTO.builder()
                        .alojamiento(reserva.getAlojamiento())
                        .usuario(reserva.getUsuario())
                        .fechaInicio(reserva.getFechaInicio())
                        .fechaFin(reserva.getFechaFin())
                        .total(reserva.getTotal())
                        .build())
                .collect(Collectors.toList());

    }


    //Validar la disponibilidad de la fecha de la reserva seleccionada
    public boolean validarDisponibilidad(Long alojamientoId, LocalDate fechaInicio, LocalDate fechaFin) {

        List<ReservaEntity> reservas = reservaRepository.findByAlojamientoId(alojamientoId);
        for (ReservaEntity reserva : reservas) {
            if (!(fechaFin.isBefore(reserva.getFechaInicio()) || fechaInicio.isAfter(reserva.getFechaFin()))) {
                return false; // Las fechas se superponen
            }
        }
        return true;

    }


}
