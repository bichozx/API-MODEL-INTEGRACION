package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import com.example.API.MODEL.INTEGRACION.ayudas.EstadosUsuario;
import com.example.API.MODEL.INTEGRACION.ayudas.RolesUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {

    @NotNull(message = "El ID no puede ser nulo")
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    @Size(max = 80, message = "El correo no debe superar los 80 caracteres")
    private String correo;

    @NotNull(message = "El estado es obligatorio")
    private EstadosUsuario estado;

    @NotNull(message = "El rol es obligatorio")
    private RolesUsuario rol;

    // Constructor vacío
    public UsuarioDTO() {}

    // Constructor completo
    public UsuarioDTO(Long id, String nombre, String correo, EstadosUsuario estado, RolesUsuario rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.estado = estado;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public EstadosUsuario getEstado() { return estado; }
    public void setEstado(EstadosUsuario estado) { this.estado = estado; }

    public RolesUsuario getRol() { return rol; }
    public void setRol(RolesUsuario rol) { this.rol = rol; }
}

