package com.example.API.MODEL.INTEGRACION.modelos.dtos;

public class AlertaAcademicaDTO {

    private String nombreEstudiante;
    private String materia;
    private Double calificacion;
    private String recomendacion;

    public AlertaAcademicaDTO(String nombreEstudiante, String materia, Double calificacion, String recomendacion) {
        this.nombreEstudiante = nombreEstudiante;
        this.materia = materia;
        this.calificacion = calificacion;
        this.recomendacion = recomendacion;
    }

    // Getters y Setters
    public String getNombreEstudiante() { return nombreEstudiante; }
    public void setNombreEstudiante(String nombreEstudiante) { this.nombreEstudiante = nombreEstudiante; }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }

    public Double getCalificacion() { return calificacion; }
    public void setCalificacion(Double calificacion) { this.calificacion = calificacion; }

    public String getRecomendacion() { return recomendacion; }
    public void setRecomendacion(String recomendacion) { this.recomendacion = recomendacion; }
}
