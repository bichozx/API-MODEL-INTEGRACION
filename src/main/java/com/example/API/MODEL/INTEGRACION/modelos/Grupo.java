package com.example.API.MODEL.INTEGRACION.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del grupo es obligatorio")
    private String nombre;

    @NotNull(message = "El semestre es obligatorio")
    private Integer semestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id", nullable = false)
    @JsonIgnoreProperties("grupos") // evita recursión con Materia
    private Materia materia;

    // Relación opcional con Matricula
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("grupo")
    private List<Matricula> matriculas;

    // Constructores
    public Grupo() {}

    public Grupo(Long id, String nombre, Integer semestre, Materia materia) {
        this.id = id;
        this.nombre = nombre;
        this.semestre = semestre;
        this.materia = materia;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getSemestre() { return semestre; }
    public void setSemestre(Integer semestre) { this.semestre = semestre; }

    public Materia getMateria() { return materia; }
    public void setMateria(Materia materia) { this.materia = materia; }

    public List<Matricula> getMatriculas() { return matriculas; }
    public void setMatriculas(List<Matricula> matriculas) { this.matriculas = matriculas; }
}
