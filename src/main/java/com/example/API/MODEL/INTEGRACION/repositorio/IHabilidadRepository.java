package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.modelos.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHabilidadRepository extends JpaRepository<Habilidad, Long> {

    // Buscar habilidades por tipo (Técnica o Blanda)
    List<Habilidad> findByTipoIgnoreCase(String tipo);

    // Buscar habilidades por nivel (ej: Básico, Intermedio, Avanzado)
    List<Habilidad> findByNivelIgnoreCase(String nivel);

    // Buscar habilidades por perfil de estudiante
    List<Habilidad> findByPerfilEstudiante_Id(Long perfilEstudianteId);
}
