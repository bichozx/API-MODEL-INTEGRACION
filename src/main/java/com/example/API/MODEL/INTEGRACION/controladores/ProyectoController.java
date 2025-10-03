package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.dtos.ProyectoDTO;
import com.example.API.MODEL.INTEGRACION.servicios.ProyectoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    // --- Crear proyecto ---
    @PostMapping
    public ResponseEntity<ProyectoDTO> crearProyecto(@Valid @RequestBody ProyectoDTO dto) {
        ProyectoDTO proyectoCreado = proyectoService.crearProyecto(dto);
        return new ResponseEntity<>(proyectoCreado, HttpStatus.CREATED);
    }

    // --- Listar todos los proyectos ---
    @GetMapping
    public ResponseEntity<List<ProyectoDTO>> listarProyectos() {
        List<ProyectoDTO> proyectos = proyectoService.listarProyectos();
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }

    // --- Obtener proyecto por ID ---
    @GetMapping("/{id}")
    public ResponseEntity<ProyectoDTO> obtenerProyectoPorId(@PathVariable Long id) {
        ProyectoDTO proyecto = proyectoService.obtenerPorId(id);
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }

    // --- Listar proyectos por perfil de estudiante ---
    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<ProyectoDTO>> obtenerProyectosPorPerfil(@PathVariable Long perfilId) {
        List<ProyectoDTO> proyectos = proyectoService.obtenerPorPerfilEstudiante(perfilId);
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }

    // --- Actualizar proyecto ---
    @PutMapping("/proyectos/{id}")
    public ResponseEntity<ProyectoDTO> actualizarProyecto(
            @PathVariable Long id,
            @Valid @RequestBody ProyectoDTO dto) {

        Long estudianteId = 1L; // Simulación: ID del estudiante autenticado
        ProyectoDTO proyectoActualizado = proyectoService.actualizarProyecto(id, dto, estudianteId);
        return ResponseEntity.ok(proyectoActualizado);
    }

    // --- Eliminar proyecto ---
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProyecto(@PathVariable Long id) {
        String mensaje = proyectoService.eliminarProyecto(id);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}

