package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.dtos.AlertaAcademicaDTO;
import com.example.API.MODEL.INTEGRACION.servicios.AlertaAcademicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/familiares")
public class AlertaAcademicaController {

    private final AlertaAcademicaService alertaAcademicaService;

    public AlertaAcademicaController(AlertaAcademicaService alertaAcademicaService) {
        this.alertaAcademicaService = alertaAcademicaService;
    }

    @GetMapping("/{id}/alertas")
    public ResponseEntity<List<AlertaAcademicaDTO>> obtenerAlertasPorFamiliar(@PathVariable Long id) {
        List<AlertaAcademicaDTO> alertas = alertaAcademicaService.obtenerAlertasPorFamiliar(id);
        return ResponseEntity.ok(alertas);
    }
}

