package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.modelos.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface INotaRepository extends JpaRepository<Nota, Long> {

    // Buscar notas de un estudiante
    List<Nota> findByEstudiante_Id(Long estudianteId);

    // Buscar notas por materia y grupo
    List<Nota> findByMateria_IdAndGrupo_Id(Long materiaId, Long grupoId);

    // Buscar notas críticas (menores a 3.0)
    List<Nota> findByEstudiante_IdAndCalificacionLessThan(Long estudianteId, Double calificacion);

    // Buscar notas de un estudiante ordenadas por fecha descendente
    List<Nota> findByEstudiante_IdOrderByFechaDesc(Long estudianteId);

    // Buscar notas críticas (con query personalizada)
    @Query("SELECT n FROM Nota n WHERE n.estudiante.id = :estudianteId AND n.calificacion < 3.0")
    List<Nota> findNotasCriticasByEstudiante(@Param("estudianteId") Long estudianteId);
}
