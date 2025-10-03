package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.dtos.PerfilEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.VinculoFamiliarEstudianteCreateDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.VinculoFamiliarEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.servicios.VinculoFamiliarEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vinculos")
public class VinculoFamiliarEstudianteController {

    @Autowired
    private VinculoFamiliarEstudianteService vinculoService;

    // Crear vínculo
    @PostMapping
    public ResponseEntity<VinculoFamiliarEstudianteDTO> crearVinculo(@Valid @RequestBody VinculoFamiliarEstudianteCreateDTO dto) {
        return ResponseEntity.ok(vinculoService.crearVinculo(dto));
    }

    // Listar todos los vínculos
    @GetMapping
    public ResponseEntity<List<VinculoFamiliarEstudianteDTO>> listarVinculos() {
        return ResponseEntity.ok(vinculoService.listarVinculos());
    }

    //listar Por Estudiante
    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<VinculoFamiliarEstudianteDTO>> listarPorEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(vinculoService.listarPorEstudiante(id));
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<VinculoFamiliarEstudianteDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vinculoService.obtenerPorId(id));
    }

    // Actualizar autorizado
    @PatchMapping("/{id}/autorizado")
    public ResponseEntity<VinculoFamiliarEstudianteDTO> actualizarAutorizado(
            @PathVariable Long id,
            @RequestParam Boolean autorizado) {
        return ResponseEntity.ok(vinculoService.actualizarAutorizado(id, autorizado));
    }

    // Eliminar vínculo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vinculoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/familiares/{id}/perfil-estudiante")
    public ResponseEntity<List<PerfilEstudianteDTO>> obtenerPerfilEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(vinculoService.obtenerPerfilEstudiantePorFamiliar(id));
    }


}
