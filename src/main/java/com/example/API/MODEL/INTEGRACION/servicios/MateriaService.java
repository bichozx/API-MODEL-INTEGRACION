package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.modelos.Materia;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.MateriaDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaMateriaDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {

    @Autowired
    private IMateriaRepository materiaRepository;

    @Autowired
    private IMapaMateriaDTO mapaMateriaDTO;

    /**
     * ✅ Crear nueva materia
     */
    public MateriaDTO crearMateria(Materia materia) {
        Materia nueva = materiaRepository.save(materia);
        return mapaMateriaDTO.toDTO(nueva);
    }

    /**
     * ✅ Listar todas las materias
     */
    public List<MateriaDTO> listarMaterias() {
        return mapaMateriaDTO.toDTOList(materiaRepository.findAll());
    }

    /**
     * ✅ Buscar materia por ID
     */
    public MateriaDTO obtenerPorId(Long id) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con ID: " + id));
        return mapaMateriaDTO.toDTO(materia);
    }

    /**
     * ✅ Buscar materias por nombre (parcial)
     */
    public List<MateriaDTO> buscarPorNombre(String nombre) {
        return mapaMateriaDTO.toDTOList(materiaRepository.findByNombreContainingIgnoreCase(nombre));
    }

    /**
     * ✅ Buscar materias por docente
     */
    public List<MateriaDTO> buscarPorDocente(Long docenteId) {
        return mapaMateriaDTO.toDTOList(materiaRepository.findByDocente_Id(docenteId));
    }

    /**
     * ✅ Eliminar materia por ID
     */
    public String eliminar(Long id) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con ID: " + id));
        materiaRepository.delete(materia);
        return "Materia eliminada correctamente.";
    }

    /**
     * ✅ Actualizar materia existente
     */
    public MateriaDTO actualizar(Long id, Materia materiaActualizada) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con ID: " + id));

        materia.setNombre(materiaActualizada.getNombre());
        materia.setCodigo(materiaActualizada.getCodigo());
        materia.setDocente(materiaActualizada.getDocente());

        Materia actualizada = materiaRepository.save(materia);
        return mapaMateriaDTO.toDTO(actualizada);
    }
}

