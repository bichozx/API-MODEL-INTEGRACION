package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.dtos.CertificadoDTO;
import com.example.API.MODEL.INTEGRACION.servicios.CertificadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificados")
public class CertificadoController {

    @Autowired
    private CertificadoService certificadoService;

    // --- Crear certificado ---
    @PostMapping("/perfil/{perfilId}")
    public ResponseEntity<CertificadoDTO> crearCertificado(
            @PathVariable Long perfilId,
            @Valid @RequestBody CertificadoDTO dto) {

        CertificadoDTO creado = certificadoService.crearCertificado(dto, perfilId);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    // --- Listar todos los certificados ---
    @GetMapping
    public ResponseEntity<List<CertificadoDTO>> listarCertificados() {
        List<CertificadoDTO> certificados = certificadoService.listarCertificados();
        return new ResponseEntity<>(certificados, HttpStatus.OK);
    }

    // --- Listar certificados por perfil ---
    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<CertificadoDTO>> listarCertificadosPorPerfil(@PathVariable Long perfilId) {
        List<CertificadoDTO> certificados = certificadoService.listarCertificadosPorPerfil(perfilId);
        return new ResponseEntity<>(certificados, HttpStatus.OK);
    }
}
