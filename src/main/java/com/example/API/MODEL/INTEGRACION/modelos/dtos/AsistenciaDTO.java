
package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import com.example.API.MODEL.INTEGRACION.ayudas.EstadoAsistencia;


import java.time.LocalDate;


public class AsistenciaDTO {
    private Long id;
    private LocalDate fecha;
    private String observacion;
    private EstadoAsistencia estado;
    private String nombreEstudiante;
    private String nombreGrupo;

    public AsistenciaDTO() {
    }

    public AsistenciaDTO(Long id, LocalDate fecha, String observacion, EstadoAsistencia estado, String nombreEstudiante, String nombreGrupo) {
        this.id = id;
        this.fecha = fecha;
        this.observacion = observacion;
        this.estado = estado;
        this.nombreEstudiante = nombreEstudiante;
        this.nombreGrupo = nombreGrupo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public EstadoAsistencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsistencia estado) {
        this.estado = estado;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }
}
