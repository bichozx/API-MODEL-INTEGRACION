package com.example.API.MODEL.INTEGRACION.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 150, message = "El título debe tener entre 3 y 150 caracteres")
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, max = 1000, message = "La descripción debe tener entre 10 y 1000 caracteres")
    @Column(nullable = false, length = 1000)
    private String descripcion;

    @Size(max = 255, message = "La URL no debe superar los 255 caracteres")
    private String url; // Link al proyecto (GitHub, portafolio, etc.)

    @Size(max = 255, message = "Las tecnologías no deben superar los 255 caracteres")
    private String tecnologias; // Ejemplo: Java, Spring Boot, React

    // Relación con PerfilEstudiante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_estudiante_id", nullable = false)
    private PerfilEstudiante perfilEstudiante;

    // Constructor vacío
    public Proyecto() {}

    // Constructor con parámetros
    public Proyecto(Long id, String titulo, String descripcion, String url, String tecnologias, PerfilEstudiante perfilEstudiante) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
        this.tecnologias = tecnologias;
        this.perfilEstudiante = perfilEstudiante;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getTecnologias() { return tecnologias; }
    public void setTecnologias(String tecnologias) { this.tecnologias = tecnologias; }

    public PerfilEstudiante getPerfilEstudiante() { return perfilEstudiante; }
    public void setPerfilEstudiante(PerfilEstudiante perfilEstudiante) { this.perfilEstudiante = perfilEstudiante; }
}

