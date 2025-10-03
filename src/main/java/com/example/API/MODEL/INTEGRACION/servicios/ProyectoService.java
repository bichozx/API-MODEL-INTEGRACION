package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.excepciones.RecursoNoEncontradoException;
import com.example.API.MODEL.INTEGRACION.modelos.PerfilEstudiante;
import com.example.API.MODEL.INTEGRACION.modelos.Proyecto;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.ProyectoDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaProyectoDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.IProyectoRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.IPerfilEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoService {

    @Autowired
    private IProyectoRepository proyectoRepository;

    @Autowired
    private IPerfilEstudianteRepository perfilRepository;

    @Autowired
    private IMapaProyectoDTO proyectoMapper;

    // Crear proyecto
    public ProyectoDTO crearProyecto(ProyectoDTO dto) {
        PerfilEstudiante perfil = perfilRepository.findById(dto.getPerfilEstudianteId().longValue())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Perfil de estudiante con id " + dto.getPerfilEstudianteId() + " no encontrado"
                ));

        Proyecto proyecto = proyectoMapper.toEntity(dto);
        proyecto.setPerfilEstudiante(perfil);

        Proyecto guardado = proyectoRepository.save(proyecto);
        return proyectoMapper.toDTO(guardado);
    }

    // Listar todos los proyectos
    public List<ProyectoDTO> listarProyectos() {
        return proyectoRepository.findAll()
                .stream()
                .map(proyectoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Obtener proyecto por ID
    public ProyectoDTO obtenerPorId(Long id) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Proyecto con id " + id + " no encontrado"
                ));
        return proyectoMapper.toDTO(proyecto);
    }

    // Listar proyectos por perfil de estudiante
    public List<ProyectoDTO> obtenerPorPerfilEstudiante(Long perfilId) {
        return proyectoRepository.findByPerfilEstudiante_Id(perfilId)
                .stream()
                .map(proyectoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Actualizar proyecto
    public ProyectoDTO actualizarProyecto(Long id, ProyectoDTO dto, Long estudianteId) {
        // Buscar proyecto
        Proyecto proyectoExistente = proyectoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Proyecto con id " + id + " no encontrado"
                ));

        // Validar que el proyecto pertenece al estudiante autenticado
        if (!proyectoExistente.getPerfilEstudiante().getEstudiante().getId().equals(estudianteId)) {
            throw new IllegalStateException("No tiene permiso para actualizar este proyecto");
        }

        // Actualizar campos
        proyectoExistente.setTitulo(dto.getTitulo());
        proyectoExistente.setDescripcion(dto.getDescripcion());
        proyectoExistente.setUrl(dto.getUrl());
        proyectoExistente.setTecnologias(dto.getTecnologias());

        // Guardar cambios y retornar DTO
        Proyecto actualizado = proyectoRepository.save(proyectoExistente);
        return proyectoMapper.toDTO(actualizado);
    }

    // Eliminar proyecto
    public String eliminarProyecto(Long id) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Proyecto con id " + id + " no encontrado"
                ));

        proyectoRepository.delete(proyecto);
        return "Proyecto con id " + id + " eliminado correctamente.";
    }
}

