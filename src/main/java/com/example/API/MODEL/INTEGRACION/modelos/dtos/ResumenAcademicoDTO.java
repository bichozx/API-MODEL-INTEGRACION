package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import java.util.List;

public class ResumenAcademicoDTO {

    private String nombreEstudiante;
    private String programa;
    private Integer semestre;
    private Double promedioGeneral;
    private Double porcentajeAsistencia;
    private List<AlertaAcademicaDTO> alertas;

    // Getters y Setters
    public String getNombreEstudiante() { return nombreEstudiante; }
    public void setNombreEstudiante(String nombreEstudiante) { this.nombreEstudiante = nombreEstudiante; }

    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }

    public Integer getSemestre() { return semestre; }
    public void setSemestre(Integer semestre) { this.semestre = semestre; }

    public Double getPromedioGeneral() { return promedioGeneral; }
    public void setPromedioGeneral(Double promedioGeneral) { this.promedioGeneral = promedioGeneral; }

    public Double getPorcentajeAsistencia() { return porcentajeAsistencia; }
    public void setPorcentajeAsistencia(Double porcentajeAsistencia) { this.porcentajeAsistencia = porcentajeAsistencia; }

    public List<AlertaAcademicaDTO> getAlertas() { return alertas; }
    public void setAlertas(List<AlertaAcademicaDTO> alertas) { this.alertas = alertas; }
}

