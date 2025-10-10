package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.dtos.*;
import com.example.API.MODEL.INTEGRACION.servicios.FamiliarService;
import com.example.API.MODEL.INTEGRACION.servicios.NecesidadApoyoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/familiares")
public class FamiliarController {

    @Autowired
    private FamiliarService familiarService;

    @Autowired
    private NecesidadApoyoService necesidadApoyoService;



    // Crear familiar usando FamiliarCreateDTO
    @PostMapping
    public ResponseEntity<FamiliarDTO> crearFamiliar(@Valid @RequestBody FamiliarCreateDTO familiarCreateDTO) {
        FamiliarDTO nuevoFamiliar = familiarService.crearFamiliar(familiarCreateDTO);
        return ResponseEntity.ok(nuevoFamiliar);
    }

    // Listar familiares
    @GetMapping
    public ResponseEntity<List<FamiliarDTO>> listarFamiliares() {
        return ResponseEntity.ok(familiarService.listarFamiliares());
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<FamiliarDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(familiarService.obtenerPorId(id));
    }

    // Actualizar familiar
    @PutMapping("/{id}/familiar")
    public ResponseEntity<FamiliarDTO> actualizarFamiliar(
            @PathVariable Long id,
            @Valid @RequestBody FamiliarDTO familiarDTO) {
        return ResponseEntity.ok(familiarService.actualizar(id, familiarDTO));
    }

    // Actualizar familiar con cambio de usuario usando FamiliarCreateDTO
    @PutMapping("/{id}/usuario")
    public ResponseEntity<FamiliarDTO> actualizarFamiliarConUsuario(
            @PathVariable Long id,
            @Valid @RequestBody FamiliarCreateDTO dto) {
        return ResponseEntity.ok(familiarService.actualizarConUsuario(id, dto));
    }

    // Eliminar familiar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFamiliar(@PathVariable Long id) {
        familiarService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Consultar notas de estudiante (para familiar)
    @GetMapping("/{id}/notas")
    public ResponseEntity<List<NotaDTO>> consultarNotas(@PathVariable Long id) {
        return ResponseEntity.ok(familiarService.consultarNotas(id));
    }

    // Consultar asistencia de estudiante (para familiar)
    @GetMapping("/{id}/asistencia")
    public ResponseEntity<List<AsistenciaSimpleDTO>> consultarAsistencia(
            @PathVariable Long id,
            @RequestParam(required = false) String fecha,
            @RequestParam(required = false) String grupo,
            @RequestParam(required = false) String materia) {

        LocalDate fechaFiltro = (fecha != null && !fecha.isBlank()) ? LocalDate.parse(fecha) : null;
        List<AsistenciaSimpleDTO> asistencias = familiarService.consultarAsistencia(id, fechaFiltro, grupo, materia);
        return ResponseEntity.ok(asistencias);
    }

    // Consultar solicitudes de bienestar autorizadas
    @GetMapping("/{id}/solicitudes-bienestar")
    public ResponseEntity<List<SolicitudBienestarDTO>> obtenerSolicitudesBienestar(@PathVariable("id") Long estudianteId) {
        List<SolicitudBienestarDTO> solicitudes = necesidadApoyoService.obtenerSolicitudesAutorizadas(estudianteId);
        return ResponseEntity.ok(solicitudes);
    }

    // ✅ HU17 – Editar datos del familiar
    @PutMapping("/{id}/")
    public ResponseEntity<FamiliarDTO> editarDatosFamiliar(
            @PathVariable Long id,
            @Valid @RequestBody FamiliarUpdateDTO dto
    ) {
        FamiliarDTO actualizado = familiarService.editarDatosFamiliar(id, dto);
        return ResponseEntity.ok(actualizado);
    }


    // ✅ HU18 – Verificar si familiar ya está registrado
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Map<String, Object>> verificarFamiliarPorCorreo(
            @PathVariable @Email(message = "Formato de correo inválido") String correo) {

        boolean existe = familiarService.existePorCorreo(correo);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("existe", existe);
        respuesta.put("mensaje", existe
                ? "Ya existe un familiar registrado con el correo: " + correo
                : "No existe ningún familiar registrado con el correo: " + correo
        );

        return ResponseEntity.ok(respuesta);
    }



}
