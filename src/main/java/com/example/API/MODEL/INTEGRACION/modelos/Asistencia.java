package com.example.API.MODEL.INTEGRACION.modelos;


import com.example.API.MODEL.INTEGRACION.ayudas.EstadoAsistencia;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "asistencia")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false, unique = false)
    private LocalDate fecha;

    @Column(name = "observacion", nullable = true, unique = false)
    private String observacion;

    @Column(name = "estado", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private EstadoAsistencia estado;

    //CREANDO UNA RELACION DE MUCHOS A UNO
    //2. COMO ME RELACIONO CON 1 SOLO ELEMENTO DE LA OTRA TABLA CREO UNA VARIABLE INDIVIDUAL
    @ManyToOne
    //3. Construyo la relacion entre las tablas (Defino la FK)
    @JoinColumn(name = "fk_estudiante", referencedColumnName = "id")
    @JsonBackReference(value = "relacionestudianteyasistencia")
    private Estudiante estudiante;


    public Asistencia() {
    }

    public Asistencia(Long id, LocalDate fecha, String observacion, EstadoAsistencia estado, Estudiante estudiante) {
        this.id = id;
        this.fecha = fecha;
        this.observacion = observacion;
        this.estado = estado;
        this.estudiante = estudiante;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public EstadoAsistencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsistencia estado) {
        this.estado = estado;
    }
}
