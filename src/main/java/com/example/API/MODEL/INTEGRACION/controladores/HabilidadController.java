package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.dtos.HabilidadDTO;
import com.example.API.MODEL.INTEGRACION.servicios.HabilidadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habilidades")
public class HabilidadController {

    @Autowired
    private HabilidadService habilidadService;

    // --- Crear habilidad ---
    @PostMapping("/perfil/{perfilId}")
    public ResponseEntity<HabilidadDTO> crearHabilidad(
            @PathVariable Long perfilId,
            @Valid @RequestBody HabilidadDTO dto) {

        HabilidadDTO creada = habilidadService.crearHabilidad(dto, perfilId);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    // --- Listar todas las habilidades ---
    @GetMapping
    public ResponseEntity<List<HabilidadDTO>> listarHabilidades() {
        List<HabilidadDTO> habilidades = habilidadService.listarHabilidades();
        return new ResponseEntity<>(habilidades, HttpStatus.OK);
    }

    // --- Listar habilidades por perfil ---
    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<HabilidadDTO>> listarHabilidadesPorPerfil(@PathVariable Long perfilId) {
        List<HabilidadDTO> habilidades = habilidadService.listarHabilidadesPorPerfil(perfilId);
        return new ResponseEntity<>(habilidades, HttpStatus.OK);
    }

    // --- Eliminar habilidad ---
    @DeleteMapping("/habilidades/{id}")
    public ResponseEntity<String> eliminarHabilidad(@PathVariable Long id) {
        Long estudianteId = 1L; // Simulación: ID del estudiante autenticado
        String mensaje = habilidadService.eliminarHabilidad(id, estudianteId);
        return ResponseEntity.ok(mensaje); // 200 OK con confirmación
    }
}

