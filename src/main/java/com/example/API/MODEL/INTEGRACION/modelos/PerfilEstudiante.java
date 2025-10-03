package com.example.API.MODEL.INTEGRACION.modelos;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "perfiles_estudiantes")
public class PerfilEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String resumen;

    @Column(length = 300)
    private String intereses;

    @Column(length = 500)
    private String experiencia;

    @OneToMany(mappedBy = "perfilEstudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proyecto> proyectos = new ArrayList<>();

    @Column(length = 100)
    private String programa;

    @Column(length = 20)
    private String semestre;

    @OneToOne
    @JoinColumn(name = "estudiante_id", nullable = false, unique = true)
    private Estudiante estudiante;

    @OneToMany(mappedBy = "perfilEstudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Habilidad> habilidades = new ArrayList<>();


    @OneToMany(mappedBy = "perfilEstudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certificado> certificados;
    public PerfilEstudiante() {}

    public PerfilEstudiante(Long id, String resumen, String intereses, String experiencia,
                            List<Proyecto> proyectos, Estudiante estudiante,
                            List<Habilidad> habilidades, String programa, String semestre,List<Certificado> certificados) {
        this.id = id;
        this.resumen = resumen;
        this.intereses = intereses;
        this.experiencia = experiencia;
        this.proyectos = proyectos != null ? proyectos : new ArrayList<>();
        this.programa = programa;
        this.semestre = semestre;
        this.estudiante = estudiante;
        this.habilidades = habilidades != null ? habilidades : new ArrayList<>();
        this.certificados = certificados;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }

    public String getIntereses() { return intereses; }
    public void setIntereses(String intereses) { this.intereses = intereses; }

    public String getExperiencia() { return experiencia; }
    public void setExperiencia(String experiencia) { this.experiencia = experiencia; }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos.clear();
        if (proyectos != null) {
            proyectos.forEach(p -> p.setPerfilEstudiante(this));
            this.proyectos.addAll(proyectos);
        }
    }


    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }

    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }

    public List<Habilidad> getHabilidades() { return habilidades; }
    public void setHabilidades(List<Habilidad> habilidades) {
        this.habilidades.clear();
        if (habilidades != null) {
            habilidades.forEach(h -> h.setPerfilEstudiante(this));
            this.habilidades.addAll(habilidades);
        }
    }

    public List<Certificado> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<Certificado> certificados) {
        this.certificados = certificados;
    }
}

