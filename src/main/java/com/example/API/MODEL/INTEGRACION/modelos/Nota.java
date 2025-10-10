package com.example.API.MODEL.INTEGRACION.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Materia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;

    // Relación con Grupo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    // Relación con Estudiante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @NotNull(message = "La calificación no puede ser nula")
    @Min(value = 0, message = "La calificación mínima es 0")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Double calificacion;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha;

    @NotNull(message = "El tipo de evaluación no puede ser nulo")
    private String tipoEvaluacion;

    public Nota() {}

    public Nota(Materia materia, Grupo grupo, Estudiante estudiante,
                Double calificacion, LocalDate fecha, String tipoEvaluacion) {
        this.materia = materia;
        this.grupo = grupo;
        this.estudiante = estudiante;
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.tipoEvaluacion = tipoEvaluacion;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Materia getMateria() { return materia; }
    public void setMateria(Materia materia) { this.materia = materia; }

    public Grupo getGrupo() { return grupo; }
    public void setGrupo(Grupo grupo) { this.grupo = grupo; }

    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

    public Double getCalificacion() { return calificacion; }
    public void setCalificacion(Double calificacion) { this.calificacion = calificacion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getTipoEvaluacion() { return tipoEvaluacion; }
    public void setTipoEvaluacion(String tipoEvaluacion) { this.tipoEvaluacion = tipoEvaluacion; }
}
