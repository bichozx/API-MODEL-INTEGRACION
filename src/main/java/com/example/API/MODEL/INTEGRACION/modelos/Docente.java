package com.example.API.MODEL.INTEGRACION.modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "docentes")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(max = 100, message = "La especialidad no debe superar los 100 caracteres")
    @Column(name = "especialidad", nullable = false, length = 100)
    private String especialidad;

    @NotBlank(message = "El nivel académico es obligatorio")
    @Size(max = 50, message = "El nivel académico no debe superar los 50 caracteres")
    @Column(name = "nivel_academico", nullable = false, length = 50)
    private String nivelAcademico;

    @NotBlank(message = "El departamento es obligatorio")
    @Size(max = 80, message = "El departamento no debe superar los 80 caracteres")
    @Column(name = "departamento", nullable = false, length = 80)
    private String departamento;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id")
    @JsonManagedReference(value = "relacionusuarioydocente")
    private Usuario usuario;

    // Constructores
    public Docente() {}

    public Docente(Long id, String especialidad, String nivelAcademico, String departamento, Usuario usuario) {
        this.id = id;
        this.especialidad = especialidad;
        this.nivelAcademico = nivelAcademico;
        this.departamento = departamento;
        this.usuario = usuario;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getNivelAcademico() { return nivelAcademico; }
    public void setNivelAcademico(String nivelAcademico) { this.nivelAcademico = nivelAcademico; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
