package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import java.time.LocalDate;

public class NecesidadApoyoDTO {
    private String tipo;
    private String descripcion;
    private LocalDate fecha;

    // Getters y Setters
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}

