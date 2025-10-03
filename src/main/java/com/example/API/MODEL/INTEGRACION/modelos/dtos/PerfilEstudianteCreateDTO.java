package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PerfilEstudianteCreateDTO {

    @Size(max = 500)
    private String resumen;

    @Size(max = 300)
    private String intereses;

    @Size(max = 500)
    private String experiencia;

    @Size(max = 500)
    private List<ProyectoDTO> proyectos;

    @NotNull(message = "El estudiante es obligatorio")
    private Long estudianteId;

    @Size(max = 255)
    @Pattern(
            regexp = "^(https?://)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)$",
            message = "La URL del proyecto no tiene un formato válido"
    )
    private String urlProyecto;

    // Getters y setters
    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }

    public String getIntereses() { return intereses; }
    public void setIntereses(String intereses) { this.intereses = intereses; }

    public String getExperiencia() { return experiencia; }
    public void setExperiencia(String experiencia) { this.experiencia = experiencia; }

    public List<ProyectoDTO> getProyectos() { return proyectos; }
    public void setProyectos(List<ProyectoDTO> proyectos) { this.proyectos = proyectos; }

    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }

    public String getUrlProyecto() { return urlProyecto; }
    public void setUrlProyecto(String urlProyecto) { this.urlProyecto = urlProyecto; }
}
