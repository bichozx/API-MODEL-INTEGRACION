package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CertificadoDTO {

    private Integer id;

    @NotBlank(message = "El nombre del certificado es obligatorio")
    @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
    private String nombre;

    @NotBlank(message = "La institución es obligatoria")
    @Size(min = 3, max = 150, message = "La institución debe tener entre 3 y 150 caracteres")
    private String institucion;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @Size(max = 255, message = "La URL no debe superar los 255 caracteres")
    private String urlArchivo;

    @NotNull(message = "El perfil de estudiante es obligatorio")
    private Integer perfilEstudianteId;

    // Constructor vacío
    public CertificadoDTO() {}

    // Constructor completo
    public CertificadoDTO(Integer id, String nombre, String institucion, LocalDate fecha, String urlArchivo, Integer perfilEstudianteId) {
        this.id = id;
        this.nombre = nombre;
        this.institucion = institucion;
        this.fecha = fecha;
        this.urlArchivo = urlArchivo;
        this.perfilEstudianteId = perfilEstudianteId;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getInstitucion() { return institucion; }
    public void setInstitucion(String institucion) { this.institucion = institucion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getUrlArchivo() { return urlArchivo; }
    public void setUrlArchivo(String urlArchivo) { this.urlArchivo = urlArchivo; }

    public Integer getPerfilEstudianteId() { return perfilEstudianteId; }
    public void setPerfilEstudianteId(Integer perfilEstudianteId) { this.perfilEstudianteId = perfilEstudianteId; }
}

