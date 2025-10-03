package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import java.util.List;

public class HojaVidaDTO {
    private Long estudianteId;
    private String nombreEstudiante; // opcional, si quieres incluir
    private String resumen;
    private String intereses;
    private String experiencia;
    private List<String> proyectos; // solo títulos o URLs
    private List<String> habilidades; // solo nombres o nivel

    // Getters y setters
    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }

    public String getNombreEstudiante() { return nombreEstudiante; }
    public void setNombreEstudiante(String nombreEstudiante) { this.nombreEstudiante = nombreEstudiante; }

    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }

    public String getIntereses() { return intereses; }
    public void setIntereses(String intereses) { this.intereses = intereses; }

    public String getExperiencia() { return experiencia; }
    public void setExperiencia(String experiencia) { this.experiencia = experiencia; }

    public List<String> getProyectos() { return proyectos; }
    public void setProyectos(List<String> proyectos) { this.proyectos = proyectos; }

    public List<String> getHabilidades() { return habilidades; }
    public void setHabilidades(List<String> habilidades) { this.habilidades = habilidades; }
}
