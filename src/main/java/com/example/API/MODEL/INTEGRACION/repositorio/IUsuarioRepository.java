package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.ayudas.EstadosUsuario;
import com.example.API.MODEL.INTEGRACION.ayudas.RolesUsuario;
import com.example.API.MODEL.INTEGRACION.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por correo
    Optional<Usuario> findByCorreo(String correo);

    // Buscar todos los usuarios por rol
    List<Usuario> findByRol(RolesUsuario rol);

    // (Opcional) Buscar todos los usuarios por estado
    List<Usuario> findByEstado(EstadosUsuario estado);

    boolean existsByCorreo(String correo);
}

