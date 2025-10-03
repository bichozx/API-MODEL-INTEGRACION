package com.example.API.MODEL.INTEGRACION.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "certificados")
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del certificado es obligatorio")
    @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
    @Column(nullable = false, length = 150)
    private String nombre;

    @NotBlank(message = "La institución es obligatoria")
    @Size(min = 3, max = 150, message = "La institución debe tener entre 3 y 150 caracteres")
    @Column(nullable = false, length = 150)
    private String institucion;

    @NotNull(message = "La fecha es obligatoria")
    @Column(nullable = false)
    private LocalDate fecha;

    @Size(max = 255, message = "La URL del archivo no debe superar los 255 caracteres")
    private String urlArchivo; // Ruta o link al archivo del certificado

    // Relación con PerfilEstudiante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_estudiante_id", nullable = false)
    private PerfilEstudiante perfilEstudiante;

    // Constructor vacío
    public Certificado() {}

    // Constructor con parámetros
    public Certificado(Long id, String nombre, String institucion, LocalDate fecha, String urlArchivo, PerfilEstudiante perfilEstudiante) {
        this.id = id;
        this.nombre = nombre;
        this.institucion = institucion;
        this.fecha = fecha;
        this.urlArchivo = urlArchivo;
        this.perfilEstudiante = perfilEstudiante;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getInstitucion() { return institucion; }
    public void setInstitucion(String institucion) { this.institucion = institucion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getUrlArchivo() { return urlArchivo; }
    public void setUrlArchivo(String urlArchivo) { this.urlArchivo = urlArchivo; }

    public PerfilEstudiante getPerfilEstudiante() { return perfilEstudiante; }
    public void setPerfilEstudiante(PerfilEstudiante perfilEstudiante) { this.perfilEstudiante = perfilEstudiante; }
}

