package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.ayudas.EstadosUsuario;
import com.example.API.MODEL.INTEGRACION.ayudas.RolesUsuario;
import com.example.API.MODEL.INTEGRACION.modelos.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Test
    void guardarYBuscarUsuarioPorCorreo() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setCorreo("juan@test.com");
        usuario.setContraseña("123456");
        usuario.setRol(RolesUsuario.Administrador);
        usuario.setEstado(EstadosUsuario.Activo);

        usuarioRepository.save(usuario);

        Optional<Usuario> encontrado = usuarioRepository.findByCorreo("juan@test.com");

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNombre()).isEqualTo("Juan");
    }

    @Test
    void buscarUsuariosPorRol() {
        Usuario usuario1 = new Usuario(null, "Ana", "ana@test.com", "123456", RolesUsuario.Estudiante, EstadosUsuario.Activo);
        Usuario usuario2 = new Usuario(null, "Pedro", "pedro@test.com", "123456", RolesUsuario.Estudiante, EstadosUsuario.Activo);
        Usuario usuario3 = new Usuario(null, "Maria", "maria@test.com", "123456", RolesUsuario.Profesor, EstadosUsuario.Activo);

        usuarioRepository.saveAll(List.of(usuario1, usuario2, usuario3));

        List<Usuario> estudiantes = usuarioRepository.findByRol(RolesUsuario.Estudiante);

        assertThat(estudiantes).hasSize(2);
        assertThat(estudiantes).extracting(Usuario::getNombre).contains("Ana", "Pedro");
    }
}

