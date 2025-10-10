package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.ayudas.EstadoAsistencia;
import com.example.API.MODEL.INTEGRACION.excepciones.RecursoNoEncontradoException;
import com.example.API.MODEL.INTEGRACION.modelos.*;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.*;
import com.example.API.MODEL.INTEGRACION.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumenAcademicoService {

    @Autowired
    private IFamiliarRepository familiarRepository;

    @Autowired
    private IVinculoFamiliarEstudianteRepository vinculoRepository;

    @Autowired
    private INotaRepository notaRepository;

    @Autowired
    private IAsistenciaRepository asistenciaRepository;

    public ResumenAcademicoDTO obtenerResumenAcademico(Long idFamiliar) {

        // 1️⃣ Verificar que el familiar exista
        Familiar familiar = familiarRepository.findById(idFamiliar)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el familiar con ID: " + idFamiliar));

        // 2️⃣ Obtener el estudiante asociado al familiar
        VinculoFamiliarEstudiante vinculo = vinculoRepository.findByFamiliar_Id(idFamiliar)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró estudiante asociado al familiar"));

        Estudiante estudiante = vinculo.getEstudiante();

        // 3️⃣ Obtener notas y calcular promedio
        List<Nota> notas = notaRepository.findByEstudiante_Id(estudiante.getId());
        double promedio = notas.stream()
                .mapToDouble(Nota::getCalificacion)
                .average()
                .orElse(0.0);

        // 4️⃣ Calcular asistencia
        List<Asistencia> asistencias = asistenciaRepository.findByEstudiante(estudiante);
        long total = asistencias.size();
        long presentes = asistencias.stream()
                .filter(a -> a.getEstado() == EstadoAsistencia.Asistio)
                .count();
        double porcentajeAsistencia = total > 0 ? (presentes * 100.0 / total) : 0.0;

        // 5️⃣ Generar alertas académicas (por ejemplo, notas bajas)
        List<AlertaAcademicaDTO> alertas = notas.stream()
                .filter(n -> n.getCalificacion() < 3.0)
                .map(n -> new AlertaAcademicaDTO(
                        estudiante.getUsuario().getNombre(),
                        n.getMateria().getNombre(), // ✅ cambio clave
                        n.getCalificacion(),
                        "Revisar desempeño en " + n.getMateria()
                ))
                .collect(Collectors.toList());

        // 6️⃣ Crear el DTO final
        ResumenAcademicoDTO resumen = new ResumenAcademicoDTO();
        resumen.setNombreEstudiante(estudiante.getUsuario().getNombre());
        resumen.setPrograma(estudiante.getPrograma());
        resumen.setSemestre(estudiante.getSemestre());
        resumen.setPromedioGeneral(promedio);
        resumen.setPorcentajeAsistencia(porcentajeAsistencia);
        resumen.setAlertas(alertas);

        return resumen;
    }
}

