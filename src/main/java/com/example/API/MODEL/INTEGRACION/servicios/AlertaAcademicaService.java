package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.modelos.Familiar;
import com.example.API.MODEL.INTEGRACION.modelos.Nota;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.AlertaAcademicaDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.IFamiliarRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.INotaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertaAcademicaService {

    private final IFamiliarRepository familiarRepository;
    private final INotaRepository notaRepository;

    public AlertaAcademicaService(IFamiliarRepository familiarRepository, INotaRepository notaRepository) {
        this.familiarRepository = familiarRepository;
        this.notaRepository = notaRepository;
    }

    public List<AlertaAcademicaDTO> obtenerAlertasPorFamiliar(Long familiarId) {
        Familiar familiar = familiarRepository.findById(familiarId)
                .orElseThrow(() -> new RuntimeException("Familiar no encontrado con ID: " + familiarId));

        // 🔹 Simulación: relacionar familiar → estudiante (en tu modelo real esto puede ser directo)
        Long estudianteId = familiar.getUsuario().getId(); // suponiendo que el usuario del familiar corresponde al estudiante

        List<Nota> notasCriticas = notaRepository.findNotasCriticasByEstudiante(estudianteId);
        List<AlertaAcademicaDTO> alertas = new ArrayList<>();

        for (Nota nota : notasCriticas) {
            String recomendacion;

            if (nota.getCalificacion() < 2.0) {
                recomendacion = "Requiere acompañamiento académico urgente.";
            } else if (nota.getCalificacion() < 2.5) {
                recomendacion = "Se recomienda tutoría con el docente.";
            } else {
                recomendacion = "Debe reforzar temas antes del próximo examen.";
            }

            alertas.add(new AlertaAcademicaDTO(
                    nota.getEstudiante().getUsuario().getNombre(),
                    nota.getMateria().getNombre(), // ✅ ahora es String
                    nota.getCalificacion(),
                    recomendacion
            ));
        }

        return alertas;
    }
}
