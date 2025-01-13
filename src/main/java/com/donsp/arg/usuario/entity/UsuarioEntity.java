package com.donsp.arg.usuario.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Setter
@Getter
@Builder
@Entity
@Table(name = "usuario")
public class UsuarioEntity {
}
