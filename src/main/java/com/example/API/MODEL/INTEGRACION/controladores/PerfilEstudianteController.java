package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.dtos.PerfilEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.PerfilEstudianteCreateDTO;
import com.example.API.MODEL.INTEGRACION.servicios.PerfilEstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/perfiles")
public class PerfilEstudianteController {

    @Autowired
    private PerfilEstudianteService perfilService;

    // --- Crear perfil (HU13) ---
    @PostMapping
    public ResponseEntity<PerfilEstudianteDTO> crearPerfil(
            @Valid @RequestBody PerfilEstudianteCreateDTO dto) {
        PerfilEstudianteDTO perfilCreado = perfilService.crearPerfil(dto);
        return new ResponseEntity<>(perfilCreado, HttpStatus.CREATED);
    }

    // --- Listar todos los perfiles ---
    @GetMapping
    public ResponseEntity<List<PerfilEstudianteDTO>> listarPerfiles() {
        List<PerfilEstudianteDTO> perfiles = perfilService.listarPerfiles();
        return new ResponseEntity<>(perfiles, HttpStatus.OK);
    }

    // --- Obtener perfil por ID ---
    @GetMapping("/{id}")
    public ResponseEntity<PerfilEstudianteDTO> obtenerPerfilPorId(@PathVariable Long id) {
        PerfilEstudianteDTO perfil = perfilService.obtenerPorId(id);
        return new ResponseEntity<>(perfil, HttpStatus.OK);
    }

    // --- Obtener perfil por estudiante ID ---
    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<PerfilEstudianteDTO> obtenerPerfilPorEstudianteId(@PathVariable Long estudianteId) {
        PerfilEstudianteDTO perfil = perfilService.obtenerPorEstudianteId(estudianteId);
        return new ResponseEntity<>(perfil, HttpStatus.OK);
    }

    // --- Actualizar perfil completo ---
    @PutMapping("/{id}")
    public ResponseEntity<PerfilEstudianteDTO> actualizarPerfil(
            @PathVariable Long id,
            @Valid @RequestBody PerfilEstudianteDTO dto) {
        PerfilEstudianteDTO perfilActualizado = perfilService.actualizar(id, dto);
        return new ResponseEntity<>(perfilActualizado, HttpStatus.OK);
    }

    // --- Eliminar perfil ---
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPerfil(@PathVariable Long id) {
        String mensaje = perfilService.eliminar(id);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    // --- HU12: Búsqueda por palabra clave ---
    @GetMapping("/busqueda")
    public ResponseEntity<List<PerfilEstudianteDTO>> buscarPerfiles(@RequestParam("query") String query) {
        List<PerfilEstudianteDTO> resultados = perfilService.buscarPerfiles(query);
        return ResponseEntity.ok(resultados);
    }


    // --- HU15: Listar perfiles paginados con filtro opcional ---
    @GetMapping("/paginado")
    public ResponseEntity<Page<PerfilEstudianteDTO>> listarPerfilesPaginado(
            @RequestParam(value = "programa", required = false) String programa,
            @RequestParam(value = "semestre", required = false) String semestre,
            Pageable pageable) {

        Page<PerfilEstudianteDTO> resultados = perfilService.listarPerfilesPaginado(programa, semestre, pageable);
        return ResponseEntity.ok(resultados);
    }

    // --- Subir URL de proyecto ---
    @PatchMapping("/{id}/proyectos")
    public ResponseEntity<PerfilEstudianteDTO> subirUrlsProyectos(
            @PathVariable Long id,
            @RequestParam List<String> urls) {

        PerfilEstudianteDTO perfilActualizado = perfilService.subirUrlProyecto(id, urls);
        return ResponseEntity.ok(perfilActualizado);
    }

}
