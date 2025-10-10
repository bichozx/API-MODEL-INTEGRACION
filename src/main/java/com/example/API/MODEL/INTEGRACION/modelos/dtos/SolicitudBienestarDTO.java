package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import java.time.LocalDate;

public class SolicitudBienestarDTO {

    private String tipo;
    private String estado;
    private LocalDate fecha;

    public SolicitudBienestarDTO(String tipo, String estado, LocalDate fecha) {
        this.tipo = tipo;
        this.estado = estado;
        this.fecha = fecha;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
