package com.example.API.MODEL.INTEGRACION.controladores;


import com.example.API.MODEL.INTEGRACION.modelos.dtos.FamiliarCreateDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.FamiliarDTO;
import com.example.API.MODEL.INTEGRACION.servicios.FamiliarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/familiares")
public class FamiliarController {

    @Autowired
    private FamiliarService familiarService;

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
    @PutMapping("/{id}")
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

}
