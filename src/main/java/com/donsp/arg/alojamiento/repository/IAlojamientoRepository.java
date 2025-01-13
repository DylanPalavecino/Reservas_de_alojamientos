package com.donsp.arg.alojamiento.repository;

import com.donsp.arg.alojamiento.entity.AlojamientoEntity;
import com.donsp.arg.alojamiento.models.TipoAlojamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAlojamientoRepository extends JpaRepository<AlojamientoEntity, Long> {

    List<AlojamientoEntity> findByTipoAlojamiento(TipoAlojamiento tipoAlojamiento);

}
