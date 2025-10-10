package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.modelos.Estudiante;
import com.example.API.MODEL.INTEGRACION.modelos.Materia;
import com.example.API.MODEL.INTEGRACION.modelos.Nota;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.NotaDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaNotaDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.IEstudianteRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.IMateriaRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.INotaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotaService {

    private final INotaRepository notaRepository;
    private final IEstudianteRepository estudianteRepository;
    private final IMateriaRepository materiaRepository;
    private final IMapaNotaDTO mapaNotaDTO;

    public NotaService(INotaRepository notaRepository,
                       IEstudianteRepository estudianteRepository,
                       IMateriaRepository materiaRepository,
                       IMapaNotaDTO mapaNotaDTO) {
        this.notaRepository = notaRepository;
        this.estudianteRepository = estudianteRepository;
        this.materiaRepository = materiaRepository;
        this.mapaNotaDTO = mapaNotaDTO;
    }

    // ✅ Crear una nueva nota (validando existencia)
    public NotaDTO crearNota(Long estudianteId, Long materiaId, NotaDTO notaDTO) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + estudianteId));

        Materia materia = materiaRepository.findById(materiaId)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con id: " + materiaId));

        Nota nota = new Nota();
        nota.setEstudiante(estudiante);
        nota.setMateria(materia);
        nota.setCalificacion(notaDTO.getCalificacion());
        nota.setFecha(java.time.LocalDate.now());
        nota.setTipoEvaluacion("Parcial"); // Puedes ajustar este valor si viene del DTO

        Nota guardada = notaRepository.save(nota);
        return mapaNotaDTO.toDTO(guardada);
    }

    // ✅ Listar todas las notas
    public List<NotaDTO> listarNotas() {
        List<Nota> notas = notaRepository.findAll();
        return mapaNotaDTO.toDTOList(notas);
    }

    // ✅ Actualizar una nota
    public NotaDTO actualizarNota(Long id, NotaDTO notaDTO) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada con id: " + id));

        nota.setCalificacion(notaDTO.getCalificacion());
        Nota actualizada = notaRepository.save(nota);

        return mapaNotaDTO.toDTO(actualizada);
    }

    // ✅ Eliminar una nota
    public void eliminarNota(Long id) {
        if (!notaRepository.existsById(id)) {
            throw new RuntimeException("No existe una nota con id: " + id);
        }
        notaRepository.deleteById(id);
    }
}

