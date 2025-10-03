package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.excepciones.UsuarioNoEncontradoException;
import com.example.API.MODEL.INTEGRACION.modelos.Estudiante;
import com.example.API.MODEL.INTEGRACION.modelos.Familiar;
import com.example.API.MODEL.INTEGRACION.modelos.PerfilEstudiante;
import com.example.API.MODEL.INTEGRACION.modelos.VinculoFamiliarEstudiante;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.PerfilEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.VinculoFamiliarEstudianteCreateDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.VinculoFamiliarEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaPerfilEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaVinculoFamiliarEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.IEstudianteRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.IFamiliarRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.IVinculoFamiliarEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VinculoFamiliarEstudianteService {

    @Autowired
    private IVinculoFamiliarEstudianteRepository vinculoRepository;

    @Autowired
    private IFamiliarRepository familiarRepository;

    @Autowired
    private IEstudianteRepository estudianteRepository;

    @Autowired
    private IMapaVinculoFamiliarEstudianteDTO vinculoMapper;

    @Autowired
    private IMapaEstudianteDTO estudianteMapper;

    @Autowired
    private IMapaPerfilEstudianteDTO perfilMapper;


    // Crear vínculo
    public VinculoFamiliarEstudianteDTO crearVinculo(VinculoFamiliarEstudianteCreateDTO dto) {
        Familiar familiar = familiarRepository.findById(dto.getFamiliarId())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Familiar con id " + dto.getFamiliarId() + " no encontrado"));
        Estudiante estudiante = estudianteRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Estudiante con id " + dto.getEstudianteId() + " no encontrado"));

        VinculoFamiliarEstudiante vinculo = vinculoMapper.toEntity(dto);
        vinculo.setFamiliar(familiar);
        vinculo.setEstudiante(estudiante);

        VinculoFamiliarEstudiante guardado = vinculoRepository.save(vinculo);
        return vinculoMapper.toDTO(guardado);
    }

    // Listar todos
    public List<VinculoFamiliarEstudianteDTO> listarVinculos() {
        return vinculoRepository.findAll().stream()
                .map(vinculoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // En el listar Por Estudiante
    public List<VinculoFamiliarEstudianteDTO> listarPorEstudiante(Long estudianteId) {
        return vinculoRepository.findByEstudiante_Id(estudianteId).stream()
                .map(vinculoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Obtener por ID
    public VinculoFamiliarEstudianteDTO obtenerPorId(Long id) {
        VinculoFamiliarEstudiante vinculo = vinculoRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Vínculo con id " + id + " no encontrado"));
        return vinculoMapper.toDTO(vinculo);
    }

    // Actualizar autorizado
    public VinculoFamiliarEstudianteDTO actualizarAutorizado(Long id, Boolean autorizado) {
        VinculoFamiliarEstudiante vinculo = vinculoRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Vínculo con id " + id + " no encontrado"));
        vinculo.setAutorizado(autorizado);
        VinculoFamiliarEstudiante actualizado = vinculoRepository.save(vinculo);
        return vinculoMapper.toDTO(actualizado);
    }

    // Eliminar
    public void eliminar(Long id) {
        VinculoFamiliarEstudiante vinculo = vinculoRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Vínculo con id " + id + " no encontrado"));
        vinculoRepository.delete(vinculo);
    }

    // 🔹 HU09: Obtener perfil del estudiante desde familiar
    public List<PerfilEstudianteDTO> obtenerPerfilEstudiantePorFamiliar(Long familiarId) {
        List<VinculoFamiliarEstudiante> vinculos = vinculoRepository.findByFamiliar_IdAndAutorizadoTrue(familiarId);

        if (vinculos.isEmpty()) {
            throw new UsuarioNoEncontradoException("No hay vínculos autorizados para el familiar con id " + familiarId);
        }

        return vinculos.stream()
                .map(v -> {
                    Estudiante estudiante = v.getEstudiante();
                    PerfilEstudiante perfil = estudiante.getPerfilEstudiante(); // 🔹 aquí necesitas un getPerfilEstudiante()
                    if (perfil == null) {
                        throw new UsuarioNoEncontradoException("El estudiante con id " + estudiante.getId() + " no tiene perfil creado");
                    }
                    return perfilMapper.toDTO(perfil);
                })
                .collect(Collectors.toList());
    }

}

