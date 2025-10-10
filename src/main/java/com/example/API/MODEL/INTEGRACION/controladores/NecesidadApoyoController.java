package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.dtos.NecesidadApoyoDTO;
import com.example.API.MODEL.INTEGRACION.servicios.NecesidadApoyoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/necesidades")
public class NecesidadApoyoController {

    private final NecesidadApoyoService service;

    public NecesidadApoyoController(NecesidadApoyoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<NecesidadApoyoDTO> registrarNecesidad(@RequestBody NecesidadApoyoDTO dto) {
        return ResponseEntity.ok(service.registrarNecesidad(dto));
    }
}

