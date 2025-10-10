package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.modelos.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMateriaRepository extends JpaRepository<Materia, Long> {

    // Buscar materias por nombre (ignorando mayúsculas/minúsculas)
    List<Materia> findByNombreContainingIgnoreCase(String nombre);

    // Buscar materias por código exacto
    Materia findByCodigo(String codigo);

    // Buscar materias por id de docente
    List<Materia> findByDocente_Id(Long docenteId);
}

