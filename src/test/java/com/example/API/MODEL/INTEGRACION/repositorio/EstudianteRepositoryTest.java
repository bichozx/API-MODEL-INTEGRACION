package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.modelos.Estudiante;
import com.example.API.MODEL.INTEGRACION.modelos.Usuario;
import com.example.API.MODEL.INTEGRACION.ayudas.EstadosUsuario;
import com.example.API.MODEL.INTEGRACION.ayudas.RolesUsuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EstudianteRepositoryTest {

    @Autowired
    private IEstudianteRepository estudianteRepository;

    @Test
    void guardarYBuscarPorNombreUsuario() {
        Usuario usuario = new Usuario(null, "Juan", "juan@test.com", "123456",
                RolesUsuario.Estudiante, EstadosUsuario.Activo);

        Estudiante estudiante = new Estudiante();
        estudiante.setPrograma("Ingeniería");
        estudiante.setSemestre(5);
        estudiante.setPromedio(4.2);
        estudiante.setFechaNacimiento(LocalDate.of(2000, 1, 1));
        estudiante.setUsuario(usuario);

        estudianteRepository.save(estudiante);

        List<Estudiante> encontrados = estudianteRepository.findByUsuario_NombreContainingIgnoreCase("juan");

        assertThat(encontrados).isNotEmpty();
        assertThat(encontrados.get(0).getPrograma()).isEqualTo("Ingeniería");
    }
}

