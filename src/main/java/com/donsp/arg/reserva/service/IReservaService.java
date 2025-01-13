package com.donsp.arg.reserva.service;

import com.donsp.arg.reserva.dto.ReservaDTO;
import com.donsp.arg.reserva.dto.request.ReservaRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IReservaService {

    public ReservaDTO generarReserva(ReservaRequest reservaRequest) throws Exception;
    public String eliminarReserva(Long id);
    public List<ReservaDTO> mostrarReservas();//Long userId);

}
