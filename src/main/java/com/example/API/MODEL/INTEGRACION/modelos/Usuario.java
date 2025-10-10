package com.example.API.MODEL.INTEGRACION.modelos;

import com.example.API.MODEL.INTEGRACION.ayudas.EstadosUsuario;
import com.example.API.MODEL.INTEGRACION.ayudas.RolesUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @Email(message = "El correo debe ser válido")
    @NotBlank(message = "El correo es obligatorio")
    @Column(unique = true, nullable = false)
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RolesUsuario rol; // Ejemplo: ADMIN, ESTUDIANTE, FAMILIAR, DOCENTE

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadosUsuario estado; // Activo o inactivo

    public Usuario() {
    }


    public Usuario(Long id, String nombre, String correo, String password, RolesUsuario rol, EstadosUsuario estado) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolesUsuario getRol() {
        return rol;
    }

    public void setRol(RolesUsuario rol) {
        this.rol = rol;
    }

    public EstadosUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadosUsuario estado) {
        this.estado = estado;
    }
}
