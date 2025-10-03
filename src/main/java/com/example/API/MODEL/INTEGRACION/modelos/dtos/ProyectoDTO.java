package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProyectoDTO {

    private Integer id;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 150, message = "El título debe tener entre 3 y 150 caracteres")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, max = 1000, message = "La descripción debe tener entre 10 y 1000 caracteres")
    private String descripcion;

    @Size(max = 255, message = "La URL no debe superar los 255 caracteres")
    private String url;

    @Size(max = 255, message = "Las tecnologías no deben superar los 255 caracteres")
    private String tecnologias;

    @NotNull(message = "El perfil de estudiante es obligatorio")
    private Integer perfilEstudianteId;

    // Constructor vacío
    public ProyectoDTO() {}

    // Constructor completo
    public ProyectoDTO(Integer id, String titulo, String descripcion, String url, String tecnologias, Integer perfilEstudianteId) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
        this.tecnologias = tecnologias;
        this.perfilEstudianteId = perfilEstudianteId;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getTecnologias() { return tecnologias; }
    public void setTecnologias(String tecnologias) { this.tecnologias = tecnologias; }

    public Integer getPerfilEstudianteId() { return perfilEstudianteId; }
    public void setPerfilEstudianteId(Integer perfilEstudianteId) { this.perfilEstudianteId = perfilEstudianteId; }
}

