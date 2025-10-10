
package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.modelos.Familiar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFamiliarRepository extends JpaRepository<Familiar, Long> {

    // Buscar familiar por correo
    Familiar findByCorreo(String correo);

    // Listar familiares asociados a un estudiante
    @Query("SELECT v.familiar FROM VinculoFamiliarEstudiante v WHERE v.estudiante.id = :estudianteId")
    List<Familiar> findFamiliaresPorEstudiante(@Param("estudianteId") Long estudianteId);

    // ✅ HU18 – Verificar existencia por correo
    boolean existsByCorreo(String correo);

}

