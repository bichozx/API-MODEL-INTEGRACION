package com.example.API.MODEL.INTEGRACION.modelos;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "necesidades_apoyo")
public class NecesidadApoyo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private String descripcion;
    private LocalDate fecha;

    @Column(name = "estado", length = 50)
    private String estado; // Ejemplo: "En revisión", "Aprobada", "Rechazada"

    @Column(name = "autorizado", nullable = false)
    private boolean autorizado; // true si el estudiante permite que su familiar lo vea

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_estudiante")
    private Estudiante estudiante;

    // Constructores
    public NecesidadApoyo() {}

    public NecesidadApoyo(String tipo, String descripcion, LocalDate fecha, String estado, boolean autorizado, Estudiante estudiante) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.autorizado = autorizado;
        this.estudiante = estudiante;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public boolean isAutorizado() { return autorizado; }
    public void setAutorizado(boolean autorizado) { this.autorizado = autorizado; }

    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
}
