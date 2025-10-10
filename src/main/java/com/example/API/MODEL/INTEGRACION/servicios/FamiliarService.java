package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.ayudas.RolesUsuario;
import com.example.API.MODEL.INTEGRACION.excepciones.UsuarioNoEncontradoException;
import com.example.API.MODEL.INTEGRACION.modelos.*;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.*;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaAsistenciaSimpleDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaFamiliarDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaNotaDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaPerfilEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Autowired
    private INotaRepository notaRepository;

    @Autowired
    private IMapaNotaDTO mapaNotaDTO;

    @Autowired
    private IAsistenciaRepository asistenciaRepository;

    @Autowired
    private IMapaAsistenciaSimpleDTO mapaAsistenciaSimpleDTO;


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

    // 🔹 Consultar historial de notas del estudiante vinculado
    public List<NotaDTO> consultarNotas(Long familiarId) {
        // Buscar vínculos autorizados
        List<VinculoFamiliarEstudiante> vinculos = vinculoRepository.findByFamiliar_IdAndAutorizadoTrue(familiarId);

        if (vinculos.isEmpty()) {
            throw new UsuarioNoEncontradoException("No hay vínculos autorizados para este familiar");
        }

        // Obtener estudiante vinculado (puedes adaptar para varios si lo deseas)
        Long estudianteId = vinculos.get(0).getEstudiante().getId();

        // Buscar notas del estudiante
        List<Nota> notas = notaRepository.findByEstudiante_Id(estudianteId);

        if (notas.isEmpty()) {
            throw new UsuarioNoEncontradoException("El estudiante no tiene notas registradas");
        }

        // Usamos el mapper de MapStruct
        return mapaNotaDTO.toDTOList(notas);
    }

    //Consultar Asistencia del estudiante--familiar
    public List<AsistenciaSimpleDTO> consultarAsistencia(Long familiarId, LocalDate fecha, String grupo, String materia) {
        // Buscar vínculos autorizados
        List<VinculoFamiliarEstudiante> vinculos = vinculoRepository.findByFamiliar_IdAndAutorizadoTrue(familiarId);

        if (vinculos.isEmpty()) {
            throw new UsuarioNoEncontradoException("No hay vínculos autorizados para este familiar");
        }

        // Tomamos el primer estudiante vinculado (o puedes devolver varios si lo deseas)
        Long estudianteId = vinculos.get(0).getEstudiante().getId();

        // Buscar asistencias filtradas
        List<Asistencia> asistencias = asistenciaRepository.filtrarAsistencias(estudianteId, fecha, grupo, materia);

        if (asistencias.isEmpty()) {
            throw new UsuarioNoEncontradoException("No hay registros de asistencia para los filtros aplicados");
        }

        // Mapear resultado a DTO
        return mapaAsistenciaSimpleDTO.toDTOList(asistencias);
    }

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder; // opcional si usas bcrypt

    // 🔹 Registro independiente desde el frontend
    public FamiliarDTO registrarFamiliarDesdeFrontend(FamiliarRegistroDTO dto) {
        // 1️⃣ Validar que el correo no exista ya como usuario
        if (usuarioRepository.existsByCorreo(dto.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está registrado en el sistema.");
        }

        // 2️⃣ Crear usuario automáticamente
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setCorreo(dto.getCorreo());
        nuevoUsuario.setNombre(dto.getNombre());
        nuevoUsuario.setRol(RolesUsuario.Familiar); // ✅ Usar directamente el valor del enum
        nuevoUsuario.setPassword(
                dto.getPassword() != null
                        ? passwordEncoder.encode(dto.getPassword()) // ✅ Codificar solo si no es null
                        : null
        );
        usuarioRepository.save(nuevoUsuario);

        // 3️⃣ Crear el familiar asociado
        Familiar nuevoFamiliar = new Familiar();
        nuevoFamiliar.setNombre(dto.getNombre());
        nuevoFamiliar.setCorreo(dto.getCorreo());
        nuevoFamiliar.setParentesco(dto.getParentesco());
        nuevoFamiliar.setTelefono(dto.getTelefono());
        nuevoFamiliar.setDireccion(dto.getDireccion());
        nuevoFamiliar.setUsuario(nuevoUsuario);

        Familiar guardado = familiarRepository.save(nuevoFamiliar);

        // 4️⃣ Devolver DTO
        return familiarMapper.toDTO(guardado);
    }

    // ✅ Actualizar datos del familiar (HU17)
    public FamiliarDTO editarDatosFamiliar(Long id, FamiliarUpdateDTO dto) {
        Familiar familiarExistente = familiarRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(
                        "Familiar con id " + id + " no encontrado"
                ));

        // Validar que el correo no esté en uso por otro familiar
        Familiar existentePorCorreo = familiarRepository.findByCorreo(dto.getCorreo());
        if (existentePorCorreo != null && !existentePorCorreo.getId().equals(id)) {
            throw new IllegalArgumentException("El correo ya está registrado por otro familiar.");
        }

        // Actualizar los campos permitidos
        familiarExistente.setNombre(dto.getNombre());
        familiarExistente.setCorreo(dto.getCorreo());
        familiarExistente.setParentesco(dto.getParentesco());
        familiarExistente.setTelefono(dto.getTelefono());
        familiarExistente.setDireccion(dto.getDireccion());

        Familiar actualizado = familiarRepository.save(familiarExistente);
        return familiarMapper.toDTO(actualizado);
    }

    // ✅ HU18 – Verificar si ya existe un familiar con ese correo
    public boolean existePorCorreo(String correo) {
        return familiarRepository.existsByCorreo(correo);
    }




}





