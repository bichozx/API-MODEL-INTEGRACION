package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import com.example.API.MODEL.INTEGRACION.ayudas.EstadoAsistencia;
import java.time.LocalDate;

public class AsistenciaSimpleDTO {
    private Long id;
    private LocalDate fecha;
    private EstadoAsistencia estado;
    private String observacion;
    private String estudiante;
    private String grupo;

    public AsistenciaSimpleDTO() {}

    public AsistenciaSimpleDTO(Long id, LocalDate fecha, EstadoAsistencia estado, String observacion, String estudiante, String grupo) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.observacion = observacion;
        this.estudiante = estudiante;
        this.grupo = grupo;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public EstadoAsistencia getEstado() { return estado; }
    public void setEstado(EstadoAsistencia estado) { this.estado = estado; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public String getEstudiante() { return estudiante; }
    public void setEstudiante(String estudiante) { this.estudiante = estudiante; }

    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }
}
