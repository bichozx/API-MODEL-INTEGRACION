package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import jakarta.validation.constraints.NotNull;

public class VinculoFamiliarEstudianteDTO {

    private Long id;
    @NotNull(message = "El campo autorizado es obligatorio")
    private Long familiarId;

    @NotNull(message = "El id del estudiante es obligatorio")
    private Long estudianteId;

    @NotNull(message = "El campo autorizado es obligatorio")
    private Boolean autorizado;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFamiliarId() { return familiarId; }
    public void setFamiliarId(Long familiarId) { this.familiarId = familiarId; }

    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }

    public Boolean getAutorizado() { return autorizado; }
    public void setAutorizado(Boolean autorizado) { this.autorizado = autorizado; }
}
