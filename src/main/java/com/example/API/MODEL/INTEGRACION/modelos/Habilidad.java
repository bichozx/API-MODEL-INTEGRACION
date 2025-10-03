package com.example.API.MODEL.INTEGRACION.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "habilidades")
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String nivel;

    @Column(nullable = false, length = 20)
    private String tipo; // Técnica o Blanda

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_estudiante_id", nullable = false)
    private PerfilEstudiante perfilEstudiante;

    public Habilidad() {}

    public Habilidad(String nombre, String nivel, String tipo, PerfilEstudiante perfilEstudiante) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = tipo;
        this.perfilEstudiante = perfilEstudiante;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public PerfilEstudiante getPerfilEstudiante() { return perfilEstudiante; }
    public void setPerfilEstudiante(PerfilEstudiante perfilEstudiante) { this.perfilEstudiante = perfilEstudiante; }
}
