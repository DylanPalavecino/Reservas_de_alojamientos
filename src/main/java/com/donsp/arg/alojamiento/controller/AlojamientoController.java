package com.donsp.arg.alojamiento.controller;

import com.donsp.arg.alojamiento.dto.AlojamientoDTO;
import com.donsp.arg.alojamiento.models.TipoAlojamiento;
import com.donsp.arg.alojamiento.service.IAlojamientoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@PreAuthorize(value = "hasRole('ROLE_ADMIN'), hasRole('ROLE_USER')")
public class AlojamientoController {

    private final IAlojamientoService alojamientoService;

    //MOSTRAR TODOS LOS ALOJAMIENTOS
    @GetMapping("/alojamientos")
    public ResponseEntity<List<AlojamientoDTO>> mostrarTodos(){

        List<AlojamientoDTO> alojamientos = alojamientoService.mostrarTodos();
        return ResponseEntity.ok(alojamientos);

    }
    //CREAR NUEVO ALOJAMIENTO
    @PostMapping("/nuevo-alojamiento")
    public ResponseEntity<AlojamientoDTO> nuevoAlojamiento(@RequestBody AlojamientoDTO alojamientoDTO){

        AlojamientoDTO alojamiento = alojamientoService.crearAlojamiento(alojamientoDTO);
        return ResponseEntity.ok(alojamiento);

    }
    //ACTUALIZAR ALOJAMIENTO
    @PostMapping("/actualizar-alojamiento")
    public ResponseEntity<AlojamientoDTO> actualizarAlojamiento(@RequestBody AlojamientoDTO alojamientoDTO, @PathVariable Long id){

        AlojamientoDTO alojamiento = alojamientoService.actualizarAlojamientoPorId(id, alojamientoDTO);
        return ResponseEntity.ok(alojamiento);

    }
    //MOSTRAR ALOJAMIENTO POR TIPO
    @GetMapping("/alojamientos/")
    public ResponseEntity<List<AlojamientoDTO>> mostrarAlojPorCategoria(@RequestParam TipoAlojamiento tipoAlojamiento){

        List<AlojamientoDTO> alojamientos = alojamientoService.buscarPorTipo(tipoAlojamiento);
        return ResponseEntity.ok(alojamientos);

    }
    //MOSTRAR ALOJAMIENTO POR ID
    @GetMapping("/alojamiento/{id}")
    public ResponseEntity<AlojamientoDTO> mostrarAlojPorId(Long id){

        AlojamientoDTO alojamiento = alojamientoService.buscarPorId(id);
        return ResponseEntity.ok(alojamiento);

    }

    @GetMapping("alojamientoenti/{id}")
    public ResponseEntity<AlojamientoDTO> mostrarAlojEntityPorId(Long id){

        AlojamientoDTO alojamiento = alojamientoService.buscarPorId(id);
        return ResponseEntity.ok(alojamiento);

    }

}
