package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.dtos.HojaVidaDTO;
import com.example.API.MODEL.INTEGRACION.servicios.HojaVidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hoja-vida")
public class HojaVidaController {

    @Autowired
    private HojaVidaService hojaVidaService;

    // Endpoint para generar hoja de vida simulada
    @GetMapping("/{estudianteId}")
    public ResponseEntity<HojaVidaDTO> generarHojaVida(@PathVariable Long estudianteId) {
        HojaVidaDTO hojaVida = hojaVidaService.generarHojaVida(estudianteId);
        return ResponseEntity.ok(hojaVida);
    }
}

