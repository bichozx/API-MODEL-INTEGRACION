package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import jakarta.validation.constraints.NotBlank;

public class HabilidadDTO {

    private Integer id;
    private String nombre;
    private String nivel;
    private String tipo;

    public HabilidadDTO() {}

    public HabilidadDTO(Integer id, String nombre, String nivel, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = tipo;
    }

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
