package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import java.util.List;

public class PerfilEstudianteCompletoDTO {

    private Long id;
    private String resumen;
    private String intereses;
    private String experiencia;
    private List<ProyectoDTO> proyectos; //  es lista, no String
    private Long estudianteId;

    private List<HabilidadDTO> habilidades;
    private List<ProyectoDTO> proyectosDetalle;
    private List<CertificadoDTO> certificados;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }

    public String getIntereses() { return intereses; }
    public void setIntereses(String intereses) { this.intereses = intereses; }

    public String getExperiencia() { return experiencia; }
    public void setExperiencia(String experiencia) { this.experiencia = experiencia; }

    public List<ProyectoDTO> getProyectos() { return proyectos; } // ✅ corregido
    public void setProyectos(List<ProyectoDTO> proyectos) { this.proyectos = proyectos; } // ✅ corregido


    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }

    public List<HabilidadDTO> getHabilidades() { return habilidades; }
    public void setHabilidades(List<HabilidadDTO> habilidades) { this.habilidades = habilidades; }

    public List<ProyectoDTO> getProyectosDetalle() { return proyectosDetalle; }
    public void setProyectosDetalle(List<ProyectoDTO> proyectosDetalle) { this.proyectosDetalle = proyectosDetalle; }

    public List<CertificadoDTO> getCertificados() { return certificados; }
    public void setCertificados(List<CertificadoDTO> certificados) { this.certificados = certificados; }
}
