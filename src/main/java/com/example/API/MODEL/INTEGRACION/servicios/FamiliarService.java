package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.excepciones.UsuarioNoEncontradoException;
import com.example.API.MODEL.INTEGRACION.modelos.Familiar;
import com.example.API.MODEL.INTEGRACION.modelos.Usuario;
import com.example.API.MODEL.INTEGRACION.modelos.VinculoFamiliarEstudiante;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.FamiliarCreateDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.FamiliarDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.PerfilEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaFamiliarDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaPerfilEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.IFamiliarRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.IUsuarioRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.IVinculoFamiliarEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FamiliarService {

    @Autowired
    private IFamiliarRepository familiarRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IMapaFamiliarDTO familiarMapper;

    @Autowired
    private IVinculoFamiliarEstudianteRepository vinculoRepository;

    @Autowired
    private IMapaPerfilEstudianteDTO perfilMapper;

    // Crear familiar usando FamiliarCreateDTO
    public FamiliarDTO crearFamiliar(FamiliarCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new UsuarioNoEncontradoException(
                        "Usuario con id " + dto.getUsuarioId() + " no encontrado"
                ));

        // Usamos MapStruct para mapear DTO de creación a entidad
        Familiar familiar = familiarMapper.toEntity(dto);
        familiar.setUsuario(usuario); // aseguramos la relación con el usuario

        Familiar guardado = familiarRepository.save(familiar);
        return familiarMapper.toDTO(guardado);
    }

    // Listar todos
    public List<FamiliarDTO> listarFamiliares() {
        return familiarRepository.findAll()
                .stream()
                .map(familiarMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Obtener por ID
    public FamiliarDTO obtenerPorId(Long id) {
        Familiar familiar = familiarRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(
                        "Familiar con id " + id + " no encontrado"
                ));
        return familiarMapper.toDTO(familiar);
    }

    // Actualizar (sigue usando FamiliarDTO)
    public FamiliarDTO actualizar(Long id, FamiliarDTO familiarDTO) {
        Familiar familiarExistente = familiarRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(
                        "Familiar con id " + id + " no encontrado"
                ));

        familiarExistente.setParentesco(familiarDTO.getParentesco());
        familiarExistente.setTelefono(familiarDTO.getTelefono());
        familiarExistente.setDireccion(familiarDTO.getDireccion());

        Familiar actualizado = familiarRepository.save(familiarExistente);
        return familiarMapper.toDTO(actualizado);
    }

    // Actualizar usando FamiliarCreateDTO (incluye usuario)
    public FamiliarDTO actualizarConUsuario(Long id, FamiliarCreateDTO dto) {
        Familiar familiarExistente = familiarRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(
                        "Familiar con id " + id + " no encontrado"
                ));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new UsuarioNoEncontradoException(
                        "Usuario con id " + dto.getUsuarioId() + " no encontrado"
                ));

        familiarExistente.setParentesco(dto.getParentesco());
        familiarExistente.setTelefono(dto.getTelefono());
        familiarExistente.setDireccion(dto.getDireccion());
        familiarExistente.setUsuario(usuario);

        Familiar actualizado = familiarRepository.save(familiarExistente);
        return familiarMapper.toDTO(actualizado);
    }

    // Eliminar
    public void eliminar(Long id) {
        Familiar familiar = familiarRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(
                        "Familiar con id " + id + " no encontrado"
                ));
        familiarRepository.delete(familiar);
    }

    // Consultar perfil del estudiante desde familiar
    public List<PerfilEstudianteDTO> obtenerPerfilEstudiantePorFamiliar(Long familiarId) {
        // Obtenemos los vínculos autorizados
        List<VinculoFamiliarEstudiante> vinculos = vinculoRepository.findByFamiliar_IdAndAutorizadoTrue(familiarId);

        if (vinculos.isEmpty()) {
            throw new UsuarioNoEncontradoException("No hay vínculos autorizados para el familiar con id " + familiarId);
        }

        // Mapear estudiantes a PerfilEstudianteDTO
        return vinculos.stream()
                .map(v -> perfilMapper.toDTO(v.getEstudiante().getPerfilEstudiante()))
                .collect(Collectors.toList());
    }




}
