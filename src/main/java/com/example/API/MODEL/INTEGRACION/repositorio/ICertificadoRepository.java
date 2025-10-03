package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.modelos.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ICertificadoRepository extends JpaRepository<Certificado, Long> {

    // Buscar certificados por perfil de estudiante
    List<Certificado> findByPerfilEstudiante_Id(Long perfilEstudianteId);

    // Buscar certificados por institución
    List<Certificado> findByInstitucionContainingIgnoreCase(String institucion);

    // Buscar certificados emitidos en una fecha específica
    List<Certificado> findByFecha(LocalDate fecha);

    // Buscar certificados por nombre
    List<Certificado> findByNombreContainingIgnoreCase(String nombre);
}
