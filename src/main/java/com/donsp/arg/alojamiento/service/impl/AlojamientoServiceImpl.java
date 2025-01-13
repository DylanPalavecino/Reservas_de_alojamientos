package com.donsp.arg.alojamiento.service.impl;

import com.donsp.arg.alojamiento.dto.AlojamientoDTO;
import com.donsp.arg.alojamiento.entity.AlojamientoEntity;
import com.donsp.arg.alojamiento.models.TipoAlojamiento;
import com.donsp.arg.alojamiento.repository.IAlojamientoRepository;
import com.donsp.arg.alojamiento.service.IAlojamientoService;
import com.donsp.arg.exception.ObjectNotFoundException;
import com.donsp.arg.usuario.dto.UsuarioDTO;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Service;
import lombok.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AlojamientoServiceImpl implements IAlojamientoService {

    private final IAlojamientoRepository alojRepository;

    //POST
    @Override
    public AlojamientoDTO crearAlojamiento(AlojamientoDTO alojamiento) {

        AlojamientoEntity alojamientoEntity = AlojamientoEntity.builder()
                .nombre(alojamiento.nombre())
                .descripcion(alojamiento.descripcion())
                .direccion(alojamiento.direccion())
                .tipoAlojamiento(alojamiento.tipoAlojamiento())
                .precioPorNoche(alojamiento.precioPorNoche())
                .capacidadPersonas(alojamiento.capacidadPersonas())
                .propietario(alojamiento.propietario())
                .build();

        alojRepository.save(alojamientoEntity);

        return AlojamientoDTO.builder()
                .nombre(alojamientoEntity.getNombre())
                .descripcion(alojamientoEntity.getDescripcion())
                .direccion(alojamientoEntity.getDireccion())
                .tipoAlojamiento(alojamientoEntity.getTipoAlojamiento())
                .precioPorNoche(alojamientoEntity.getPrecioPorNoche())
                .capacidadPersonas(alojamientoEntity.getCapacidadPersonas())
                .propietario(alojamientoEntity.getPropietario())
                .build();
    }
    //GET BY ID
    @Override
    public AlojamientoDTO buscarPorId(Long id) {

        AlojamientoEntity alojamientoEntity = alojRepository.findById(id).orElseThrow(()->new ObjectNotFoundException("Alojamiento no encontrado"));
        return AlojamientoDTO.builder()
                .nombre(alojamientoEntity.getNombre())
                .descripcion(alojamientoEntity.getDescripcion())
                .direccion(alojamientoEntity.getDireccion())
                .tipoAlojamiento(alojamientoEntity.getTipoAlojamiento())
                .precioPorNoche(alojamientoEntity.getPrecioPorNoche())
                .capacidadPersonas(alojamientoEntity.getCapacidadPersonas())
                .propietario(alojamientoEntity.getPropietario())
                .build();

    }

    //GET ALL
    @Override
    public List<AlojamientoDTO> mostrarTodos() {

        List<AlojamientoEntity> alojamientos = alojRepository.findAll();
        return alojamientos.stream()
                .map(alojamientoEntity -> AlojamientoDTO.builder()
                        .nombre(alojamientoEntity.getNombre())
                        .descripcion(alojamientoEntity.getDescripcion())
                        .direccion(alojamientoEntity.getDireccion())
                        .tipoAlojamiento(alojamientoEntity.getTipoAlojamiento())
                        .precioPorNoche(alojamientoEntity.getPrecioPorNoche())
                        .capacidadPersonas(alojamientoEntity.getCapacidadPersonas())
                        .propietario(alojamientoEntity.getPropietario())
                        .build())
                        .collect(Collectors.toList());

    }

    //GET BY TYPE
    @Override
    public List<AlojamientoDTO> buscarPorTipo(TipoAlojamiento tipoAlojamiento) {

        if (tipoAlojamiento == null){
            throw new IllegalArgumentException("Tipo de alojamiento no puede ser nulo.");
        }

        List<AlojamientoEntity> alojamientos = alojRepository.findByTipoAlojamiento(tipoAlojamiento);

        if (alojamientos.isEmpty()){
            throw new NoSuchElementException("No se encontraron alojamientos de este tipo");
        }

        return alojamientos.stream().map(alojamientoEntity -> AlojamientoDTO.builder()
                        .nombre(alojamientoEntity.getNombre())
                        .descripcion(alojamientoEntity.getDescripcion())
                        .direccion(alojamientoEntity.getDireccion())
                        .tipoAlojamiento(alojamientoEntity.getTipoAlojamiento())
                        .precioPorNoche(alojamientoEntity.getPrecioPorNoche())
                        .capacidadPersonas(alojamientoEntity.getCapacidadPersonas())
                        .propietario(alojamientoEntity.getPropietario())
                        .build())
                        .collect(Collectors.toList());


    }

    //DELETE BY ID
    @Override
    public String borrarAlojamientoPorId(Long id) {

        alojRepository.deleteById(id);
        return "Alojamiento borrado correctamente";
    }

    //UPDATE BY ID
    @Override
    public AlojamientoDTO actualizarAlojamientoPorId(Long id, AlojamientoDTO alojamiento) {

        AlojamientoEntity alojamientoEntity = alojRepository.findById(id).orElseThrow(()->new ObjectNotFoundException("Alojamiento no valido o no encontrado"));

        AlojamientoEntity.builder()
                .nombre(alojamiento.nombre())
                .descripcion(alojamiento.descripcion())
                .direccion(alojamiento.direccion())
                .tipoAlojamiento(alojamiento.tipoAlojamiento())
                .precioPorNoche(alojamiento.precioPorNoche())
                .capacidadPersonas(alojamiento.capacidadPersonas())
                .propietario(alojamiento.propietario())
                .build();

        alojRepository.save(alojamientoEntity);

        return AlojamientoDTO.builder()
                .nombre(alojamientoEntity.getNombre())
                .descripcion(alojamientoEntity.getDescripcion())
                .direccion(alojamientoEntity.getDireccion())
                .tipoAlojamiento(alojamientoEntity.getTipoAlojamiento())
                .precioPorNoche(alojamientoEntity.getPrecioPorNoche())
                .capacidadPersonas(alojamientoEntity.getCapacidadPersonas())
                .propietario(alojamientoEntity.getPropietario())
                .build();

    }

    //OBTENER ENTIDAD PARA OTRO SERVICE
    @Override
    public AlojamientoEntity buscarEntityPorId(Long id) {

        AlojamientoEntity alojamiento = alojRepository.findById(id).orElseThrow(()->new ObjectNotFoundException("Alojamiento no encontrado"));
        return alojamiento;

    }


}
