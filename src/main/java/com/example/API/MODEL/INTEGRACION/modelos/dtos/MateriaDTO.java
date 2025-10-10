package com.example.API.MODEL.INTEGRACION.modelos.dtos;

public class MateriaDTO {
    private Long id;
    private String nombre;
    private String codigo;
    private String docenteNombre;

    public MateriaDTO() {}

    public MateriaDTO(Long id, String nombre, String codigo, String docenteNombre) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.docenteNombre = docenteNombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDocenteNombre() {
        return docenteNombre;
    }

    public void setDocenteNombre(String docenteNombre) {
        this.docenteNombre = docenteNombre;
    }
}
