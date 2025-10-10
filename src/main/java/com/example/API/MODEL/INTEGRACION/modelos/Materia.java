package com.example.API.MODEL.INTEGRACION.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la materia es obligatorio")
    private String nombre;

    @NotBlank(message = "El código de la materia es obligatorio")
    @Column(unique = true)
    private String codigo;

    // Relación con Docente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docente_id", nullable = false)
    @JsonIgnoreProperties("materias") // evita recursión en serialización
    private Docente docente;

    // Relación bidireccional con Grupo
    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("materia") // evita bucles infinitos
    private List<Grupo> grupos;

    // Constructores
    public Materia() {}

    public Materia(Long id, String nombre, String codigo, Docente docente) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.docente = docente;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Docente getDocente() { return docente; }
    public void setDocente(Docente docente) { this.docente = docente; }

    public List<Grupo> getGrupos() { return grupos; }
    public void setGrupos(List<Grupo> grupos) { this.grupos = grupos; }
}
