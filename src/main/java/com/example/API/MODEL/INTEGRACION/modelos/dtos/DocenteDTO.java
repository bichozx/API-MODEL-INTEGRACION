package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DocenteDTO {

    private Integer id;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(min = 3, max = 100, message = "La especialidad debe tener entre 3 y 100 caracteres")
    private String especialidad;

    @NotBlank(message = "El nivel académico es obligatorio")
    @Size(min = 2, max = 50, message = "El nivel académico debe tener entre 2 y 50 caracteres")
    private String nivelAcademico;

    @NotBlank(message = "El departamento es obligatorio")
    @Size(min = 2, max = 100, message = "El departamento debe tener entre 2 y 100 caracteres")
    private String departamento;

    @NotNull(message = "El usuario es obligatorio")
    private UsuarioDTO usuario;

    public DocenteDTO() {
    }

    public DocenteDTO(Integer id, String especialidad, String nivelAcademico, String departamento, UsuarioDTO usuario) {
        this.id = id;
        this.especialidad = especialidad;
        this.nivelAcademico = nivelAcademico;
        this.departamento = departamento;
        this.usuario = usuario;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getNivelAcademico() { return nivelAcademico; }
    public void setNivelAcademico(String nivelAcademico) { this.nivelAcademico = nivelAcademico; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public UsuarioDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario; }
}
