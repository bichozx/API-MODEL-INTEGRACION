package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.excepciones.RecursoNoEncontradoException;
import com.example.API.MODEL.INTEGRACION.modelos.PerfilEstudiante;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.PerfilEstudianteCompletoDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaCertificadoDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaHabilidadDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaPerfilEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaProyectoDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.ICertificadoRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.IHabilidadRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.IPerfilEstudianteRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.IProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PerfilCompletoService {

    @Autowired
    private IPerfilEstudianteRepository perfilRepository;

    @Autowired
    private IProyectoRepository proyectoRepository;

    @Autowired
    private IHabilidadRepository habilidadRepository;

    @Autowired
    private ICertificadoRepository certificadoRepository;

    @Autowired
    private IMapaPerfilEstudianteDTO perfilMapper;

    @Autowired
    private IMapaProyectoDTO proyectoMapper;

    @Autowired
    private IMapaHabilidadDTO habilidadMapper;

    @Autowired
    private IMapaCertificadoDTO certificadoMapper;

    public PerfilEstudianteCompletoDTO obtenerPerfilCompleto(Long estudianteId) {
        PerfilEstudiante perfil = perfilRepository.findByEstudiante_Id(estudianteId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Perfil del estudiante con id " + estudianteId + " no encontrado"
                ));

        PerfilEstudianteCompletoDTO dto = new PerfilEstudianteCompletoDTO();
        dto.setId(perfil.getId());
        dto.setResumen(perfil.getResumen());
        dto.setIntereses(perfil.getIntereses());
        dto.setExperiencia(perfil.getExperiencia());
        //dto.setProyectos(perfil.getProyectos()); // campos de texto si aplica

        // Mapear habilidades
        dto.setHabilidades(
                habilidadRepository.findByPerfilEstudiante_Id(perfil.getId())
                        .stream()
                        .map(habilidadMapper::toDTO)
                        .collect(Collectors.toList())
        );

        // Mapear proyectos
        dto.setProyectosDetalle(
                proyectoRepository.findByPerfilEstudiante_Id(perfil.getId())
                        .stream()
                        .map(proyectoMapper::toDTO)
                        .collect(Collectors.toList())
        );

        // Mapear certificados
        dto.setCertificados(
                certificadoRepository.findByPerfilEstudiante_Id(perfil.getId())
                        .stream()
                        .map(certificadoMapper::toDTO)
                        .collect(Collectors.toList())
        );

        return dto;
    }
}
