package com.example.API.MODEL.INTEGRACION.controladores;

import com.example.API.MODEL.INTEGRACION.modelos.dtos.ResumenAcademicoDTO;
import com.example.API.MODEL.INTEGRACION.servicios.ResumenAcademicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/familiares")
public class ResumenAcademicoController {

    @Autowired
    private ResumenAcademicoService resumenAcademicoService;

    @GetMapping("/{id}/resumen-academico")
    public ResponseEntity<ResumenAcademicoDTO> obtenerResumen(@PathVariable Long id) {
        ResumenAcademicoDTO resumen = resumenAcademicoService.obtenerResumenAcademico(id);
        return ResponseEntity.ok(resumen);
    }
}
