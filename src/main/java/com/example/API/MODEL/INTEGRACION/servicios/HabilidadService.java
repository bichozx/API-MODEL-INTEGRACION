package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.excepciones.RecursoNoEncontradoException;
import com.example.API.MODEL.INTEGRACION.modelos.Habilidad;
import com.example.API.MODEL.INTEGRACION.modelos.PerfilEstudiante;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.HabilidadDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaHabilidadDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.IHabilidadRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.IPerfilEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HabilidadService {

    @Autowired
    private IHabilidadRepository habilidadRepository;

    @Autowired
    private IPerfilEstudianteRepository perfilRepository;

    @Autowired
    private IMapaHabilidadDTO habilidadMapper;

    // Crear habilidad
    public HabilidadDTO crearHabilidad(HabilidadDTO dto, Long perfilEstudianteId) {
        PerfilEstudiante perfil = perfilRepository.findById(perfilEstudianteId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Perfil de estudiante con id " + perfilEstudianteId + " no encontrado"
                ));

        Habilidad habilidad = new Habilidad(dto.getNombre(), dto.getNivel(), dto.getTipo(), perfil);
        Habilidad guardada = habilidadRepository.save(habilidad);
        return habilidadMapper.toDTO(guardada);
    }

    // Listar todas las habilidades
    public List<HabilidadDTO> listarHabilidades() {
        return habilidadRepository.findAll()
                .stream()
                .map(habilidadMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Listar habilidades por perfil de estudiante
    public List<HabilidadDTO> listarHabilidadesPorPerfil(Long perfilEstudianteId) {
        return habilidadRepository.findByPerfilEstudiante_Id(perfilEstudianteId)
                .stream()
                .map(habilidadMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Eliminar habilidad con validación de propiedad
    public String eliminarHabilidad(Long habilidadId, Long estudianteId) {
        Habilidad habilidad = habilidadRepository.findById(habilidadId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Habilidad con id " + habilidadId + " no encontrada"
                ));

        // Validar que la habilidad pertenece al estudiante
        if (!habilidad.getPerfilEstudiante().getEstudiante().getId().equals(estudianteId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "No tiene permisos para eliminar esta habilidad");
        }

        habilidadRepository.delete(habilidad);
        return "Habilidad con id " + habilidadId + " eliminada correctamente.";
    }

}

