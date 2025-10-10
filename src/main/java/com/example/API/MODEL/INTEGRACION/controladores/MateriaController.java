package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.Materia;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.MateriaDTO;
import com.example.API.MODEL.INTEGRACION.servicios.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    /**
     * ✅ Crear nueva materia
     */
    @PostMapping
    public ResponseEntity<MateriaDTO> crear(@RequestBody Materia materia) {
        return ResponseEntity.ok(materiaService.crearMateria(materia));
    }

    /**
     * ✅ Listar todas las materias
     */
    @GetMapping
    public ResponseEntity<List<MateriaDTO>> listar() {
        return ResponseEntity.ok(materiaService.listarMaterias());
    }

    /**
     * ✅ Obtener una materia por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(materiaService.obtenerPorId(id));
    }

    /**
     * ✅ Buscar materias por nombre (parcial)
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<MateriaDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(materiaService.buscarPorNombre(nombre));
    }

    /**
     * ✅ Buscar materias por ID del docente
     */
    @GetMapping("/docente/{docenteId}")
    public ResponseEntity<List<MateriaDTO>> buscarPorDocente(@PathVariable Long docenteId) {
        return ResponseEntity.ok(materiaService.buscarPorDocente(docenteId));
    }

    /**
     * ✅ Actualizar materia existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<MateriaDTO> actualizar(@PathVariable Long id, @RequestBody Materia materia) {
        return ResponseEntity.ok(materiaService.actualizar(id, materia));
    }

    /**
     * ✅ Eliminar una materia por su ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(materiaService.eliminar(id));
    }
}
