package com.example.API.MODEL.INTEGRACION.modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "vinculos_familiar_estudiante")
public class VinculoFamiliarEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "familiar_id", nullable = false)
    @JsonManagedReference(value = "relacionfamiliarvinculo")
    private Familiar familiar;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "estudiante_id", nullable = false)
    @JsonManagedReference(value = "relacionestudiantevinculo")
    private Estudiante estudiante;

    @NotNull(message = "El campo autorizado es obligatorio")
    @Column(nullable = false)
    private Boolean autorizado;

    // Constructores
    public VinculoFamiliarEstudiante() {}

    public VinculoFamiliarEstudiante(Familiar familiar, Estudiante estudiante, Boolean autorizado) {
        this.familiar = familiar;
        this.estudiante = estudiante;
        this.autorizado = autorizado;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Familiar getFamiliar() { return familiar; }
    public void setFamiliar(Familiar familiar) { this.familiar = familiar; }

    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

    public Boolean getAutorizado() { return autorizado; }
    public void setAutorizado(Boolean autorizado) { this.autorizado = autorizado; }
}
