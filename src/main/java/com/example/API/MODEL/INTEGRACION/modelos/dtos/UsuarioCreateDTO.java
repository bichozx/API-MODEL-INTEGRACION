package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import com.example.API.MODEL.INTEGRACION.ayudas.EstadosUsuario;
import com.example.API.MODEL.INTEGRACION.ayudas.RolesUsuario;
import jakarta.validation.constraints.*;

public class UsuarioCreateDTO extends UsuarioDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe ser válido")
    @Size(max = 80, message = "El correo no debe superar los 80 caracteres")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener mínimo 6 caracteres")
    private String contraseña;

    @NotNull(message = "El estado es obligatorio")
    private EstadosUsuario estado;

    @NotNull(message = "El rol es obligatorio")
    private RolesUsuario rol;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public EstadosUsuario getEstado() { return estado; }
    public void setEstado(EstadosUsuario estado) { this.estado = estado; }

    public RolesUsuario getRol() { return rol; }
    public void setRol(RolesUsuario rol) { this.rol = rol; }
}