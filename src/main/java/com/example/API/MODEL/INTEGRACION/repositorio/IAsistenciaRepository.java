package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.modelos.Asistencia;
import com.example.API.MODEL.INTEGRACION.modelos.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IAsistenciaRepository extends JpaRepository<Asistencia, Long> {

    // Buscar asistencias por estudiante
    List<Asistencia> findByEstudiante_Id(Long estudianteId);

    // Filtrar por fecha exacta
    List<Asistencia> findByFecha(LocalDate fecha);

    // Buscar por rango de fechas
    List<Asistencia> findByFechaBetween(LocalDate inicio, LocalDate fin);

    List<Asistencia> findByEstudiante(Estudiante estudiante);

    // ✅ Consulta corregida: Asistencia → Grupo → Materia
    @Query("""
        SELECT a 
        FROM Asistencia a 
        WHERE a.estudiante.id = :estudianteId
        AND (:fecha IS NULL OR a.fecha = :fecha)
        AND (:grupo IS NULL OR LOWER(a.grupo.nombre) LIKE LOWER(CONCAT('%', :grupo, '%')))
        AND (:materia IS NULL OR LOWER(a.grupo.materia.nombre) LIKE LOWER(CONCAT('%', :materia, '%')))
    """)
    List<Asistencia> filtrarAsistencias(
            @Param("estudianteId") Long estudianteId,
            @Param("fecha") LocalDate fecha,
            @Param("grupo") String grupo,
            @Param("materia") String materia
    );
}
