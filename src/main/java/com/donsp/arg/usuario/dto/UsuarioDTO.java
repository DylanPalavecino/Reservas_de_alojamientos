package com.donsp.arg.usuario.dto;

import lombok.Builder;

@Builder
public record UsuarioDTO (String username, String name, String role, String jwt){
}
