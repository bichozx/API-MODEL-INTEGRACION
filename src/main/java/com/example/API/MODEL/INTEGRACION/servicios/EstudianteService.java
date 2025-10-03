package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.excepciones.UsuarioNoEncontradoException;
import com.example.API.MODEL.INTEGRACION.modelos.Estudiante;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.EstudianteDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.IEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    @Autowired
    private IEstudianteRepository estudianteRepository;

    @Autowired
    private IMapaEstudianteDTO estudianteMapper;

    // Crear estudiante
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Estudiante guardado = estudianteRepository.save(estudiante);
        return estudianteMapper.toDTO(guardado);
    }

    // Listar estudiantes
    public List<EstudianteDTO> listarEstudiantes() {
        return estudianteRepository.findAll()
                .stream()
                .map(estudianteMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Obtener estudiante por ID
    public EstudianteDTO obtenerPorId(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Estudiante con id " + id + " no encontrado"));
        return estudianteMapper.toDTO(estudiante);
    }

    // Actualizar estudiante
    public EstudianteDTO actualizar(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudianteExistente = estudianteRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Estudiante con id " + id + " no encontrado"));

        estudianteExistente.setPrograma(estudianteDTO.getPrograma());
        estudianteExistente.setSemestre(estudianteDTO.getSemestre());
        estudianteExistente.setPromedio(estudianteDTO.getPromedio());
        estudianteExistente.setFechaNacimiento(estudianteDTO.getFechaNacimiento());

        Estudiante actualizado = estudianteRepository.save(estudianteExistente);
        return estudianteMapper.toDTO(actualizado);
    }

    // Eliminar estudiante
    public void eliminar(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Estudiante con id " + id + " no encontrado"));
        estudianteRepository.delete(estudiante);
    }
}
