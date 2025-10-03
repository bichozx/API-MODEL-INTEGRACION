package com.example.API.MODEL.INTEGRACION.repositorio;

import com.example.API.MODEL.INTEGRACION.modelos.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDocenteRepository extends JpaRepository<Docente, Long> {
}

