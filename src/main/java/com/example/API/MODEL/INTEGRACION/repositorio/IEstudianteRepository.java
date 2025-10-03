package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.modelos.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IEstudianteRepository extends JpaRepository<Estudiante, Long> {

    // Buscar estudiantes por nombre del usuario relacionado
    List<Estudiante> findByUsuario_NombreContainingIgnoreCase(String nombre);


    // Si en el futuro agregas un campo "codigoEstudiante", lo puedes usar así:
    // Optional<Estudiante> findByCodigoEstudiante(String codigo);

}
