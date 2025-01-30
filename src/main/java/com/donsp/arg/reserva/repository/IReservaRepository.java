package com.donsp.arg.reserva.repository;

import com.donsp.arg.reserva.entity.ReservaEntity;
import com.donsp.arg.usuario.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReservaRepository extends JpaRepository<ReservaEntity, Long> {

    List<ReservaEntity> findByAlojamientoId(Long alojamientoId);
    List<ReservaEntity> findByUsuario(User usuario);

}
