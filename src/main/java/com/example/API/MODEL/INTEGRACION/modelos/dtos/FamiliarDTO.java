package com.example.API.MODEL.INTEGRACION.modelos.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FamiliarDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe superar 100 caracteres")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ser un correo válido")
    @Size(max = 100, message = "El correo no debe superar 100 caracteres")
    private String correo;

    @NotBlank(message = "El parentesco es obligatorio")
    @Size(min = 2, max = 50, message = "El parentesco debe tener entre 2 y 50 caracteres")
    private String parentesco;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 7, max = 20, message = "El teléfono debe tener entre 7 y 20 caracteres")
    private String telefono;

    @Size(max = 150, message = "La dirección no debe superar 150 caracteres")
    private String direccion;

    @NotNull(message = "El usuario asociado es obligatorio")
    private UsuarioDTO usuario;

    public FamiliarDTO() {}

    public FamiliarDTO(Long id, String nombre, String correo, String parentesco, String telefono, String direccion, UsuarioDTO usuario) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.parentesco = parentesco;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public UsuarioDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario; }
}
