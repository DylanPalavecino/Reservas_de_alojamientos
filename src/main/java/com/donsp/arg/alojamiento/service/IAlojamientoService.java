package com.donsp.arg.alojamiento.service;

import com.donsp.arg.alojamiento.dto.AlojamientoDTO;
import com.donsp.arg.alojamiento.entity.AlojamientoEntity;
import com.donsp.arg.alojamiento.models.TipoAlojamiento;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IAlojamientoService {


    public AlojamientoDTO crearAlojamiento(AlojamientoDTO alojamiento);
    public AlojamientoDTO buscarPorId(Long id);
    public List<AlojamientoDTO> mostrarTodos();
    public List<AlojamientoDTO> buscarPorTipo(TipoAlojamiento tipoAlojamiento);
    public String borrarAlojamientoPorId(Long id);
    public AlojamientoDTO actualizarAlojamientoPorId(Long id, AlojamientoDTO alojamiento);

    //Para otras entidades
    public AlojamientoEntity buscarEntityPorId(Long id);
}
