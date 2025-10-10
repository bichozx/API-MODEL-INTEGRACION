package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FamiliarRegistroDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ser un correo válido")
    private String correo;

    @NotBlank(message = "El parentesco es obligatorio")
    @Size(max = 50)
    private String parentesco;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20)
    private String telefono;

    @Size(max = 150)
    private String direccion;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getParentesco() { return parentesco; }
    public void setParentesco(String parentesco) { this.parentesco = parentesco; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
