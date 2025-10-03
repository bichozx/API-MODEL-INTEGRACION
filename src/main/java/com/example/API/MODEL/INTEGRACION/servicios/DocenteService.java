package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.excepciones.UsuarioNoEncontradoException;
import com.example.API.MODEL.INTEGRACION.modelos.Docente;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.DocenteDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaDocenteDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.IDocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocenteService {

    @Autowired
    private IDocenteRepository docenteRepository;

    @Autowired
    private IMapaDocenteDTO docenteMapper;

    // Crear docente
    public DocenteDTO crearDocente(DocenteDTO docenteDTO) {
        Docente docente = docenteMapper.toEntity(docenteDTO);
        Docente guardado = docenteRepository.save(docente);
        return docenteMapper.toDTO(guardado);
    }

    // Listar docentes
    public List<DocenteDTO> listarDocentes() {
        return docenteRepository.findAll()
                .stream()
                .map(docenteMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Obtener por ID
    public DocenteDTO obtenerPorId(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Docente con id " + id + " no encontrado"));
        return docenteMapper.toDTO(docente);
    }

    // Actualizar docente
    public DocenteDTO actualizar(long id, DocenteDTO docenteDTO) {
        Docente docenteExistente = docenteRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Docente con id " + id + " no encontrado"));

        docenteExistente.setEspecialidad(docenteDTO.getEspecialidad());
        docenteExistente.setNivelAcademico(docenteDTO.getNivelAcademico());
        docenteExistente.setDepartamento(docenteDTO.getDepartamento());

        Docente actualizado = docenteRepository.save(docenteExistente);
        return docenteMapper.toDTO(actualizado);
    }

    // Eliminar docente
    public void eliminar(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Docente con id " + id + " no encontrado"));
        docenteRepository.delete(docente);
    }
}
