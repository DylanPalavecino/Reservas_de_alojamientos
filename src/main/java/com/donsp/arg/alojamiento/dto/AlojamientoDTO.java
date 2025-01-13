package com.donsp.arg.alojamiento.dto;

import com.donsp.arg.alojamiento.models.TipoAlojamiento;
import com.donsp.arg.usuario.dto.UsuarioDTO;
import com.donsp.arg.usuario.entity.UsuarioEntity;
import lombok.*;


@Builder
public record AlojamientoDTO (String nombre,
         String descripcion,
         String direccion,
         TipoAlojamiento tipoAlojamiento,
         Double precioPorNoche,
         Integer capacidadPersonas,
         UsuarioEntity propietario){}

