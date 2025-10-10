package com.example.API.MODEL.INTEGRACION.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
        name = "matriculas",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"estudiante_id", "materia_id", "periodo"})
        }
)
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El estudiante es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    @JsonIgnoreProperties("matriculas") // evita recursión si agregas relación inversa
    private Estudiante estudiante;

    @NotNull(message = "La materia es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id", nullable = false)
    @JsonIgnoreProperties({"grupos", "docente"})
    private Materia materia;

    @NotNull(message = "El grupo es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id", nullable = false)
    @JsonIgnoreProperties({"materia", "matriculas"})
    private Grupo grupo;

    @NotBlank(message = "El periodo es obligatorio")
    private String periodo;

    // Constructores
    public Matricula() {}

    public Matricula(Long id, Estudiante estudiante, Materia materia, Grupo grupo, String periodo) {
        this.id = id;
        this.estudiante = estudiante;
        this.materia = materia;
        this.grupo = grupo;
        this.periodo = periodo;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

    public Materia getMateria() { return materia; }
    public void setMateria(Materia materia) { this.materia = materia; }

    public Grupo getGrupo() { return grupo; }
    public void setGrupo(Grupo grupo) { this.grupo = grupo; }

    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
}
