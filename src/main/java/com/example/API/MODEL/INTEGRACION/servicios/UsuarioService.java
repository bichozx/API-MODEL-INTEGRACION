package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.ayudas.RolesUsuario;
import com.example.API.MODEL.INTEGRACION.excepciones.UsuarioNoEncontradoException;
import com.example.API.MODEL.INTEGRACION.modelos.Usuario;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.UsuarioDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.UsuarioCreateDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaUsuarioDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IMapaUsuarioDTO usuarioMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Crear usuario (con UsuarioCreateDTO)
    public UsuarioDTO crearUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioCreateDTO);

        // Encriptar la contraseña antes de guardar
        String contraseñaEncriptada = passwordEncoder.encode(usuarioCreateDTO.getContraseña());
        usuario.setContraseña(contraseñaEncriptada);

        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(guardado);
    }

    // Listar usuarios
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Listar usuarios por rol
    public List<UsuarioDTO> listarPorRol(String rol) {
        try {
            // Convertir string recibido a Enum
            RolesUsuario rolEnum = RolesUsuario.valueOf(rol.toUpperCase());

            return usuarioRepository.findByRol(rolEnum)
                    .stream()
                    .map(usuarioMapper::toDTO)
                    .collect(Collectors.toList());

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("El rol " + rol + " no es válido. Usa un valor permitido.");
        }
    }


    // Obtener usuario por ID
    public UsuarioDTO obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario con id " + id + " no encontrado"));
        return usuarioMapper.toDTO(usuario);
    }

    // Actualizar usuario con validaciones
    public UsuarioDTO actualizar(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new UsuarioNoEncontradoException("Usuario con id " + id + " no encontrado"));

        // Validar que el correo no esté repetido en otro usuario
        usuarioRepository.findByCorreo(usuarioDTO.getCorreo())
                .ifPresent(usuarioConCorreo -> {
                    if (!usuarioConCorreo.getId().equals(id)) {
                        throw new DataIntegrityViolationException(
                                "El correo " + usuarioDTO.getCorreo() + " ya está en uso por otro usuario."
                        );
                    }
                });

        // Actualizar solo los campos permitidos
        usuarioExistente.setNombre(usuarioDTO.getNombre());
        usuarioExistente.setCorreo(usuarioDTO.getCorreo());
        usuarioExistente.setEstado(usuarioDTO.getEstado());
        usuarioExistente.setRol(usuarioDTO.getRol());

        Usuario actualizado = usuarioRepository.save(usuarioExistente);
        return usuarioMapper.toDTO(actualizado);
    }


    // Eliminar usuario con validación
    public String eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(
                        "Usuario con id " + id + " no encontrado"));

        usuarioRepository.delete(usuario);
        return "Usuario con id " + id + " eliminado correctamente.";
    }




}
