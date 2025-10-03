
package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.dtos.DocenteDTO;
import com.example.API.MODEL.INTEGRACION.servicios.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    // Crear docente
    @PostMapping
    public ResponseEntity<DocenteDTO> crearDocente(@Valid @RequestBody DocenteDTO docenteDTO) {
        return ResponseEntity.ok(docenteService.crearDocente(docenteDTO));
    }

    // Listar docentes
    @GetMapping
    public ResponseEntity<List<DocenteDTO>> listarDocentes() {
        return ResponseEntity.ok(docenteService.listarDocentes());
    }

    // Obtener docente por ID
    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(docenteService.obtenerPorId(id));
    }

    // Actualizar docente
    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> actualizarDocente(
            @PathVariable Integer id,
            @Valid @RequestBody DocenteDTO docenteDTO) {
        return ResponseEntity.ok(docenteService.actualizar(id, docenteDTO));
    }

    // Eliminar docente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDocente(@PathVariable Long id) {
        docenteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
