package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class EstudianteDTO {

    private Long id;

    @NotBlank(message = "El programa es obligatorio")
    @Size(min = 3, max = 100, message = "El programa debe tener entre 3 y 100 caracteres")
    private String programa;

    @NotNull(message = "El semestre es obligatorio")
    @Min(value = 1, message = "El semestre debe ser mínimo 1")
    @Max(value = 12, message = "El semestre no puede ser mayor a 12")
    private Integer semestre;

    @NotNull(message = "El promedio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El promedio no puede ser menor a 0.0")
    @DecimalMax(value = "5.0", inclusive = true, message = "El promedio no puede ser mayor a 5.0")
    private Double promedio;

    private LocalDate fechaNacimiento;

    @NotNull(message = "El usuario es obligatorio")
    private UsuarioDTO usuario;

    public EstudianteDTO() {
    }

    public EstudianteDTO(Long id, UsuarioDTO usuario, LocalDate fechaNacimiento, Double promedio, Integer semestre, String programa) {
        this.id = id;
        this.usuario = usuario;
        this.fechaNacimiento = fechaNacimiento;
        this.promedio = promedio;
        this.semestre = semestre;
        this.programa = programa;
    }


    // Getters y Setters
    public long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }

    public Integer getSemestre() { return semestre; }
    public void setSemestre(Integer semestre) { this.semestre = semestre; }

    public Double getPromedio() { return promedio; }
    public void setPromedio(Double promedio) { this.promedio = promedio; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public UsuarioDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario; }
}
