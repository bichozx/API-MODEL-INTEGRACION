package com.example.API.MODEL.INTEGRACION.modelos;

import com.example.API.MODEL.INTEGRACION.ayudas.EstadoAsistencia;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "asistencia")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "observacion")
    private String observacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoAsistencia estado;

    // 🔹 Relación con Estudiante
    @ManyToOne
    @JoinColumn(name = "fk_estudiante", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "relacionestudianteyasistencia")
    private Estudiante estudiante;

    // 🔹 Relación con Grupo
    @ManyToOne
    @JoinColumn(name = "fk_grupo", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "relaciongrupoyasistencia")
    private Grupo grupo;

    public Asistencia() {}

    public Asistencia(Long id, LocalDate fecha, String observacion, EstadoAsistencia estado, Estudiante estudiante, Grupo grupo) {
        this.id = id;
        this.fecha = fecha;
        this.observacion = observacion;
        this.estado = estado;
        this.estudiante = estudiante;
        this.grupo = grupo;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public EstadoAsistencia getEstado() { return estado; }
    public void setEstado(EstadoAsistencia estado) { this.estado = estado; }

    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

    public Grupo getGrupo() { return grupo; }
    public void setGrupo(Grupo grupo) { this.grupo = grupo; }
}
