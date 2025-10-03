package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.excepciones.RecursoNoEncontradoException;
import com.example.API.MODEL.INTEGRACION.modelos.PerfilEstudiante;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.HojaVidaDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.IHabilidadRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.IPerfilEstudianteRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.IProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class HojaVidaService {

    @Autowired
    private IPerfilEstudianteRepository perfilRepository;

    @Autowired
    private IProyectoRepository proyectoRepository;

    @Autowired
    private IHabilidadRepository habilidadRepository;

    public HojaVidaDTO generarHojaVida(Long estudianteId) {
        PerfilEstudiante perfil = perfilRepository.findByEstudiante_Id(estudianteId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Perfil del estudiante con id " + estudianteId + " no encontrado"
                ));

        HojaVidaDTO hojaVida = new HojaVidaDTO();
        hojaVida.setEstudianteId(estudianteId);
        hojaVida.setNombreEstudiante(perfil.getEstudiante().getUsuario().getNombre());
        // si tienes nombre
        hojaVida.setResumen(perfil.getResumen());
        hojaVida.setIntereses(perfil.getIntereses());
        hojaVida.setExperiencia(perfil.getExperiencia());

        hojaVida.setProyectos(
                proyectoRepository.findByPerfilEstudiante_Id(perfil.getId())
                        .stream()
                        .map(p -> p.getTitulo() + " (" + p.getUrl() + ")") // título + url
                        .collect(Collectors.toList())
        );

        hojaVida.setHabilidades(
                habilidadRepository.findByPerfilEstudiante_Id(perfil.getId())
                        .stream()
                        .map(h -> h.getNombre() + " - " + h.getNivel())
                        .collect(Collectors.toList())
        );

        return hojaVida;
    }
}
