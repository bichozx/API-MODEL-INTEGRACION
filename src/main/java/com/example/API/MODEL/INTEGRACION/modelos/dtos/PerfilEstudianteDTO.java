package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import com.example.API.MODEL.INTEGRACION.modelos.Proyecto;

import java.util.List;

public class PerfilEstudianteDTO {

    private Long id;
    private String resumen;
    private String intereses;
    private String experiencia;
    //private String proyectos;
    private List<ProyectoDTO> proyectos; //  es lista, no String
    private Long estudianteId;
    private List<HabilidadDTO> habilidades;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }

    public String getIntereses() { return intereses; }
    public void setIntereses(String intereses) { this.intereses = intereses; }

    public String getExperiencia() { return experiencia; }
    public void setExperiencia(String experiencia) { this.experiencia = experiencia; }

    //public String getProyectos() { return proyectos; }
    //public void setProyectos(String proyectos) { this.proyectos = proyectos; }

    public List<ProyectoDTO> getProyectos() { return proyectos; }
    public void setProyectos(List<ProyectoDTO> proyectos) { this.proyectos = proyectos; }

    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }

    public List<HabilidadDTO> getHabilidades() { return habilidades; }
    public void setHabilidades(List<HabilidadDTO> habilidades) { this.habilidades = habilidades; }


}
