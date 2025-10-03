package com.example.API.MODEL.INTEGRACION.modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "programa", nullable = false, length = 100)
    private String programa;

    @Column(name = "semestre", nullable = false)
    private Integer semestre;

    @Column(name = "promedio", nullable = false)
    private Double promedio;

    @Column(name = "fecha_nacimiento", nullable = true)
    private LocalDate fechaNacimiento;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id")
    @JsonManagedReference(value = "relacionentreusuarioyestudiante")
    private Usuario usuario;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "relacionestudianteyasistencia")
    private List<Asistencia> asistencias = new ArrayList<>();

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PerfilEstudiante perfilEstudiante;

    public Estudiante() {}

    public Estudiante(Long id, String programa, Integer semestre, Double promedio, LocalDate fechaNacimiento, Usuario usuario) {
        this.id = id;
        this.programa = programa;
        this.semestre = semestre;
        this.promedio = promedio;
        this.fechaNacimiento = fechaNacimiento;
        this.usuario = usuario;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }

    public Integer getSemestre() { return semestre; }
    public void setSemestre(Integer semestre) { this.semestre = semestre; }

    public Double getPromedio() { return promedio; }
    public void setPromedio(Double promedio) { this.promedio = promedio; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<Asistencia> getAsistencias() { return asistencias; }
    public void setAsistencias(List<Asistencia> asistencias) { this.asistencias = asistencias; }

    public PerfilEstudiante getPerfilEstudiante() {
        return perfilEstudiante;
    }

    public void setPerfilEstudiante(PerfilEstudiante perfilEstudiante) {
        this.perfilEstudiante = perfilEstudiante;
    }
}
