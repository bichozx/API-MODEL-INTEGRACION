package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class FamiliarUpdateDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe superar 100 caracteres")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ser un correo válido")
    @Size(max = 100, message = "El correo no debe superar 100 caracteres")
    private String correo;

    @NotBlank(message = "El parentesco es obligatorio")
    @Size(max = 50, message = "El parentesco no debe superar 50 caracteres")
    private String parentesco;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9()+\\-\\s]+$", message = "El teléfono solo puede contener números y símbolos válidos (+, -, espacio)")
    @Size(min = 7, max = 20, message = "El teléfono debe tener entre 7 y 20 caracteres")
    private String telefono;

    @Size(max = 150, message = "La dirección no debe superar 150 caracteres")
    private String direccion;

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
}
