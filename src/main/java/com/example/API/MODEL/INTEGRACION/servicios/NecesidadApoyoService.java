package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.modelos.NecesidadApoyo;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.NecesidadApoyoDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.SolicitudBienestarDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaNecesidadApoyoDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.INecesidadApoyoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NecesidadApoyoService {

    private final INecesidadApoyoRepository repo;
    private final IMapaNecesidadApoyoDTO mapper;

    //  Único constructor con dependencias inyectadas
    public NecesidadApoyoService(INecesidadApoyoRepository repo, IMapaNecesidadApoyoDTO mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    //  Registrar nueva necesidad de apoyo
    public NecesidadApoyoDTO registrarNecesidad(NecesidadApoyoDTO dto) {
        NecesidadApoyo entidad = mapper.toEntity(dto);
        NecesidadApoyo guardada = repo.save(entidad);
        return mapper.toDTO(guardada);
    }

    //  Obtener solicitudes autorizadas por estudiante
    public List<SolicitudBienestarDTO> obtenerSolicitudesAutorizadas(Long estudianteId) {
        List<NecesidadApoyo> solicitudes = repo.findAutorizadasByEstudiante(estudianteId);
        return solicitudes.stream()
                .map(s -> new SolicitudBienestarDTO(s.getTipo(), s.getEstado(), s.getFecha()))
                .collect(Collectors.toList());
    }
}

