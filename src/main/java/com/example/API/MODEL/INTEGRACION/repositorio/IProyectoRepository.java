package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.modelos.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProyectoRepository extends JpaRepository<Proyecto, Long> {

    // Buscar proyectos por perfil de estudiante
    List<Proyecto> findByPerfilEstudiante_Id(Long perfilEstudianteId);

    // Buscar proyectos por tecnología (ej: "Java", "React")
    List<Proyecto> findByTecnologiasContainingIgnoreCase(String tecnologia);

    // Buscar proyectos por título
    List<Proyecto> findByTituloContainingIgnoreCase(String titulo);
}
