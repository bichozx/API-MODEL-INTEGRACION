package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.dtos.*;
import com.example.API.MODEL.INTEGRACION.servicios.VinculoFamiliarEstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vinculos")
public class VinculoFamiliarEstudianteController {

    @Autowired
    private VinculoFamiliarEstudianteService vinculoService;

    @PostMapping
    public ResponseEntity<VinculoFamiliarEstudianteDTO> crearVinculo(@Valid @RequestBody VinculoFamiliarEstudianteCreateDTO dto) {
        return ResponseEntity.ok(vinculoService.crearVinculo(dto));
    }

    @GetMapping
    public ResponseEntity<List<VinculoFamiliarEstudianteDTO>> listarVinculos() {
        return ResponseEntity.ok(vinculoService.listarVinculos());
    }

    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<VinculoFamiliarEstudianteDTO>> listarPorEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(vinculoService.listarPorEstudiante(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VinculoFamiliarEstudianteDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vinculoService.obtenerPorId(id));
    }

    @PatchMapping("/{id}/autorizado")
    public ResponseEntity<VinculoFamiliarEstudianteDTO> actualizarAutorizado(
            @PathVariable Long id, @RequestParam Boolean autorizado) {
        return ResponseEntity.ok(vinculoService.actualizarAutorizado(id, autorizado));
    }

    // ✅ Endpoint HU16: Eliminar vínculo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        String mensaje = vinculoService.eliminar(id);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/familiares/{id}/perfil-estudiante")
    public ResponseEntity<List<PerfilEstudianteDTO>> obtenerPerfilEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(vinculoService.obtenerPerfilEstudiantePorFamiliar(id));
    }

    // ✅ HU20 – Listar todos los vínculos registrados con nombres
    @GetMapping("/detallado")
    public ResponseEntity<List<VinculoFamiliarEstudianteViewDTO>> listarVinculosDetallados() {
        return ResponseEntity.ok(vinculoService.listarVinculosConNombres());
    }

}
