package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class NotaDTO {

    private Long id;

    @NotNull(message = "La materia no puede ser nula")
    private String materia;

    @NotNull(message = "La calificación no puede ser nula")
    @Min(value = 0, message = "La calificación mínima es 0")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Double calificacion;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha;

    @NotNull(message = "El tipo de evaluación no puede ser nulo")
    private String tipoEvaluacion;

    public NotaDTO() {}

    public NotaDTO(Long id, String materia, Double calificacion, LocalDate fecha, String tipoEvaluacion) {
        this.id = id;
        this.materia = materia;
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }

    public Double getCalificacion() { return calificacion; }
    public void setCalificacion(Double calificacion) { this.calificacion = calificacion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getTipoEvaluacion() { return tipoEvaluacion; }
    public void setTipoEvaluacion(String tipoEvaluacion) { this.tipoEvaluacion = tipoEvaluacion; }
}
