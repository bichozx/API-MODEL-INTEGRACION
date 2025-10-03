package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.dtos.PerfilEstudianteCompletoDTO;
import com.example.API.MODEL.INTEGRACION.servicios.PerfilCompletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil-estudiante")
public class PerfilCompletoController {

    @Autowired
    private PerfilCompletoService perfilCompletoService;

    // Obtener perfil completo por estudiante
    @GetMapping("/{estudianteId}")
    public ResponseEntity<PerfilEstudianteCompletoDTO> obtenerPerfilCompleto(
            @PathVariable Long estudianteId) {

        PerfilEstudianteCompletoDTO perfilCompleto =
                perfilCompletoService.obtenerPerfilCompleto(estudianteId);

        return ResponseEntity.ok(perfilCompleto);
    }
}
