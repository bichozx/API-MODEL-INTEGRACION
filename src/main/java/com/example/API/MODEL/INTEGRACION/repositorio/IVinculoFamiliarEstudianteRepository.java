package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.modelos.VinculoFamiliarEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IVinculoFamiliarEstudianteRepository extends JpaRepository<VinculoFamiliarEstudiante, Long> {

    List<VinculoFamiliarEstudiante> findByEstudiante_Id(Long estudianteId);
    List<VinculoFamiliarEstudiante> findByFamiliar_Id(Long familiarId);

    // Nuevo método: solo vínculos autorizados
    List<VinculoFamiliarEstudiante> findByFamiliar_IdAndAutorizadoTrue(Long familiarId);
}
