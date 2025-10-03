package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.dtos.EstudianteDTO;
import com.example.API.MODEL.INTEGRACION.servicios.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // Crear estudiante
    @PostMapping
    public ResponseEntity<EstudianteDTO> crearEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO nuevoEstudiante = estudianteService.crearEstudiante(estudianteDTO);
        return ResponseEntity.ok(nuevoEstudiante);
    }

    // Listar todos los estudiantes
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listarEstudiantes() {
        List<EstudianteDTO> estudiantes = estudianteService.listarEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }

    // Obtener estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> obtenerPorId(@PathVariable Long id) {
        EstudianteDTO estudiante = estudianteService.obtenerPorId(id);
        return ResponseEntity.ok(estudiante);
    }

    // Actualizar estudiante
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(
            @PathVariable Long id,
            @Valid @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO actualizado = estudianteService.actualizar(id, estudianteDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
