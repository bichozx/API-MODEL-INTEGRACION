package com.example.API.MODEL.INTEGRACION.modelos.dtos;

public class VinculoFamiliarEstudianteViewDTO {

    private Long id;
    private String nombreFamiliar;
    private String nombreEstudiante;
    private Boolean autorizado;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreFamiliar() { return nombreFamiliar; }
    public void setNombreFamiliar(String nombreFamiliar) { this.nombreFamiliar = nombreFamiliar; }

    public String getNombreEstudiante() { return nombreEstudiante; }
    public void setNombreEstudiante(String nombreEstudiante) { this.nombreEstudiante = nombreEstudiante; }

    public Boolean getAutorizado() { return autorizado; }
    public void setAutorizado(Boolean autorizado) { this.autorizado = autorizado; }
}
