package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.modelos.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IRepositorioAsistencia extends JpaRepository<Asistencia, Long> {

    List<Asistencia> findByEstudianteId(Long estudianteId);

    List<Asistencia> findByGrupoId(Long grupoId);

    List<Asistencia> findByFechaBetween(LocalDate inicio, LocalDate fin);
}
