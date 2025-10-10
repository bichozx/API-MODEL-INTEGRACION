package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.excepciones.RecursoNoEncontradoException;
import com.example.API.MODEL.INTEGRACION.modelos.*;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.*;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaVinculoFamiliarEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VinculoFamiliarEstudianteService {

    @Autowired
    private IVinculoFamiliarEstudianteRepository vinculoRepository;

    @Autowired
    private IEstudianteRepository estudianteRepository;

    @Autowired
    private IFamiliarRepository familiarRepository;

    @Autowired
    private IPerfilEstudianteRepository perfilRepository;

    @Autowired
    private IMapaVinculoFamiliarEstudianteDTO vinculoMapper;

    // 🔹 Crear vínculo
    @Transactional
    public VinculoFamiliarEstudianteDTO crearVinculo(VinculoFamiliarEstudianteCreateDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Estudiante no encontrado con id: " + dto.getEstudianteId()));

        Familiar familiar = familiarRepository.findById(dto.getFamiliarId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Familiar no encontrado con id: " + dto.getFamiliarId()));

        VinculoFamiliarEstudiante vinculo = new VinculoFamiliarEstudiante(familiar, estudiante, dto.getAutorizado());
        VinculoFamiliarEstudiante guardado = vinculoRepository.save(vinculo);

        return vinculoMapper.toDTO(guardado);
    }

    // 🔹 Listar todos
    public List<VinculoFamiliarEstudianteDTO> listarVinculos() {
        return vinculoRepository.findAll()
                .stream()
                .map(vinculoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 🔹 Listar por estudiante
    public List<VinculoFamiliarEstudianteDTO> listarPorEstudiante(Long estudianteId) {
        return vinculoRepository.findByEstudiante_Id(estudianteId)
                .stream()
                .map(vinculoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 🔹 Obtener por ID
    public VinculoFamiliarEstudianteDTO obtenerPorId(Long id) {
        VinculoFamiliarEstudiante vinculo = vinculoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Vínculo no encontrado con id: " + id));
        return vinculoMapper.toDTO(vinculo);
    }

    // 🔹 Actualizar campo "autorizado"
    @Transactional
    public VinculoFamiliarEstudianteDTO actualizarAutorizado(Long id, Boolean autorizado) {
        VinculoFamiliarEstudiante vinculo = vinculoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Vínculo no encontrado con id: " + id));

        vinculo.setAutorizado(autorizado);
        VinculoFamiliarEstudiante actualizado = vinculoRepository.save(vinculo);
        return vinculoMapper.toDTO(actualizado);
    }

    // 🔹 Eliminar vínculo (HU16)
    @Transactional
    public String eliminar(Long id) {
        VinculoFamiliarEstudiante vinculo = vinculoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Vínculo no encontrado con id: " + id));

        vinculoRepository.delete(vinculo);
        return "Vínculo eliminado exitosamente con id: " + id;
    }

    // 🔹 Obtener perfil de estudiantes vinculados
    public List<PerfilEstudianteDTO> obtenerPerfilEstudiantePorFamiliar(Long familiarId) {
        List<VinculoFamiliarEstudiante> vinculos = vinculoRepository.findByFamiliar_IdAndAutorizadoTrue(familiarId);

        return vinculos.stream()
                .map(v -> perfilRepository.findByEstudiante_Id(v.getEstudiante().getId()))
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .map(perfil -> {
                    PerfilEstudianteDTO dto = new PerfilEstudianteDTO();
                    dto.setId(perfil.getId());
                    dto.setResumen(perfil.getResumen());
                    dto.setExperiencia(perfil.getExperiencia());
                    dto.setIntereses(perfil.getIntereses());
                    dto.setEstudianteId(perfil.getEstudiante().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // 🔹 HU20 – Listar todos los vínculos con nombres
    public List<VinculoFamiliarEstudianteViewDTO> listarVinculosConNombres() {
        return vinculoRepository.findAll()
                .stream()
                .map(vinculoMapper::toViewDTO)
                .collect(Collectors.toList());
    }
}
