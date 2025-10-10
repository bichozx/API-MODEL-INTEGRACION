package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.modelos.NecesidadApoyo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface INecesidadApoyoRepository extends JpaRepository<NecesidadApoyo, Long> {

    @Query("SELECT n FROM NecesidadApoyo n WHERE n.estudiante.id = :estudianteId AND n.autorizado = true")
    List<NecesidadApoyo> findAutorizadasByEstudiante(@Param("estudianteId") Long estudianteId);
}

