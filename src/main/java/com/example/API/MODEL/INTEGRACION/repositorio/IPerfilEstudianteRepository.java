package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.modelos.PerfilEstudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPerfilEstudianteRepository extends JpaRepository<PerfilEstudiante, Long> {

    // Buscar perfil por id del estudiante (relación @OneToOne)
    Optional<PerfilEstudiante> findByEstudiante_Id(Long estudianteId);

    // Verificar si un estudiante ya tiene perfil
    boolean existsByEstudiante_Id(Long estudianteId);

    // Búsqueda por palabra clave en resumen o intereses
    @Query("SELECT p FROM PerfilEstudiante p " +
            "WHERE LOWER(p.resumen) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.intereses) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<PerfilEstudiante> buscarPorPalabraClave(@Param("query") String query);

    // Paginación y filtrado opcional por programa y semestre
    Page<PerfilEstudiante> findByProgramaContainingIgnoreCaseAndSemestreContainingIgnoreCase(
            String programa,
            String semestre,
            Pageable pageable
    );
}
