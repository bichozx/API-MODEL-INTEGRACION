package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.excepciones.RecursoNoEncontradoException;
import com.example.API.MODEL.INTEGRACION.modelos.Habilidad;
import com.example.API.MODEL.INTEGRACION.modelos.PerfilEstudiante;
import com.example.API.MODEL.INTEGRACION.modelos.Proyecto;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.PerfilEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.PerfilEstudianteCreateDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaPerfilEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.IPerfilEstudianteRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.IEstudianteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfilEstudianteService {

    @Autowired
    private IPerfilEstudianteRepository perfilRepository;

    @Autowired
    private IEstudianteRepository estudianteRepository;

    @Autowired
    private IMapaPerfilEstudianteDTO perfilMapper;

    // --- Crear perfil con validación HU13 ---
    public PerfilEstudianteDTO crearPerfil(PerfilEstudianteCreateDTO dto) {
        var estudiante = estudianteRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Estudiante con id " + dto.getEstudianteId() + " no encontrado"
                ));

        // Validación HU13: un estudiante solo puede tener un perfil
        if (perfilRepository.existsByEstudiante_Id(dto.getEstudianteId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El estudiante ya tiene un perfil registrado"
            );
        }

        PerfilEstudiante perfil = perfilMapper.toEntity(dto);
        perfil.setEstudiante(estudiante);

        PerfilEstudiante guardado = perfilRepository.save(perfil);
        return perfilMapper.toDTO(guardado);
    }

    // --- Listar todos los perfiles ---
    public List<PerfilEstudianteDTO> listarPerfiles() {
        return perfilRepository.findAll()
                .stream()
                .map(perfilMapper::toDTO)
                .collect(Collectors.toList());
    }

    // --- Obtener perfil por ID ---
    public PerfilEstudianteDTO obtenerPorId(Long id) {
        PerfilEstudiante perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Perfil con id " + id + " no encontrado"
                ));
        return perfilMapper.toDTO(perfil);
    }

    // --- Obtener perfil por estudiante ID ---
    public PerfilEstudianteDTO obtenerPorEstudianteId(Long estudianteId) {
        PerfilEstudiante perfil = perfilRepository.findByEstudiante_Id(estudianteId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Perfil para estudiante con id " + estudianteId + " no encontrado"
                ));
        return perfilMapper.toDTO(perfil);
    }

    // --- Actualizar perfil completo ---
    public PerfilEstudianteDTO actualizar(Long id, PerfilEstudianteDTO dto) {
        PerfilEstudiante perfilExistente = perfilRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Perfil con id " + id + " no encontrado"
                ));

        perfilExistente.setResumen(dto.getResumen());
        perfilExistente.setIntereses(dto.getIntereses());
        perfilExistente.setExperiencia(dto.getExperiencia());

        if (dto.getProyectos() != null) {
            List<Proyecto> proyectos = dto.getProyectos().stream()
                    .map(proyDTO -> {
                        Proyecto p = new Proyecto();
                        p.setTitulo(proyDTO.getTitulo());
                        p.setDescripcion(proyDTO.getDescripcion());
                        p.setUrl(proyDTO.getUrl());
                        p.setTecnologias(proyDTO.getTecnologias());
                        p.setPerfilEstudiante(perfilExistente); // relación bidireccional
                        return p;
                    })
                    .collect(Collectors.toList());

            perfilExistente.setProyectos(proyectos);
        }

        if (dto.getHabilidades() != null) {
            List<Habilidad> mappedHabilidades = dto.getHabilidades().stream()
                    .map(h -> new Habilidad(
                            h.getNombre(),
                            h.getNivel(),
                            h.getTipo(),
                            perfilExistente
                    ))
                    .collect(Collectors.toList());
            perfilExistente.setHabilidades(mappedHabilidades);
        }

        PerfilEstudiante actualizado = perfilRepository.save(perfilExistente);
        return perfilMapper.toDTO(actualizado);
    }
    // --- Eliminar perfil ---
    public String eliminar(Long id) {
        PerfilEstudiante perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Perfil con id " + id + " no encontrado"
                ));

        perfilRepository.delete(perfil);
        return "Perfil con id " + id + " eliminado correctamente.";
    }

    // --- HU12: Búsqueda por palabra clave ---
    public List<PerfilEstudianteDTO> buscarPerfiles(String query) {
        List<PerfilEstudiante> perfiles = perfilRepository.buscarPorPalabraClave(query);
        return perfiles.stream()
                .map(perfilMapper::toDTO)
                .collect(Collectors.toList());
    }

    // --- HU15: Listar perfiles con paginación y filtro ---
    public Page<PerfilEstudianteDTO> listarPerfilesPaginado(String programa, String semestre, Pageable pageable) {
        String programaFilter = (programa != null) ? programa : "";
        String semestreFilter = (semestre != null) ? semestre : "";

        Page<PerfilEstudiante> page = perfilRepository
                .findByProgramaContainingIgnoreCaseAndSemestreContainingIgnoreCase(programaFilter, semestreFilter, pageable);

        return page.map(perfilMapper::toDTO);
    }

    // --- Subir URL del proyecto ---
    public PerfilEstudianteDTO subirUrlProyecto(Long id, List<String> urls) {
        PerfilEstudiante perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Perfil con id " + id + " no encontrado"));

        for (String url : urls) {
            if (url != null && !url.matches("^(https?://).+")) {
                throw new IllegalArgumentException("La URL debe comenzar con http:// o https://");
            }

            Proyecto nuevoProyecto = new Proyecto();
            nuevoProyecto.setUrl(url);
            nuevoProyecto.setPerfilEstudiante(perfil);
            perfil.getProyectos().add(nuevoProyecto);
        }

        PerfilEstudiante actualizado = perfilRepository.save(perfil);
        return perfilMapper.toDTO(actualizado);
    }


}
