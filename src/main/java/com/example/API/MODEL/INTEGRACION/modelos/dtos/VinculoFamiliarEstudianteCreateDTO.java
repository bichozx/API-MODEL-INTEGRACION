package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import jakarta.validation.constraints.NotNull;

public class VinculoFamiliarEstudianteCreateDTO {

    @NotNull(message = "El ID del familiar es obligatorio")
    private Long familiarId;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long estudianteId;

    @NotNull(message = "El campo autorizado es obligatorio")
    private Boolean autorizado;

    // Getters y Setters
    public Long getFamiliarId() { return familiarId; }
    public void setFamiliarId(Long familiarId) { this.familiarId = familiarId; }

    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }

    public Boolean getAutorizado() { return autorizado; }
    public void setAutorizado(Boolean autorizado) { this.autorizado = autorizado; }
}

