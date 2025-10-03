package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.PerfilEstudiante;
import com.example.API.MODEL.INTEGRACION.modelos.Habilidad;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.PerfilEstudianteDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.PerfilEstudianteCreateDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.HabilidadDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.ProyectoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
uses = {IMapaHabilidadDTO.class, IMapaProyectoDTO.class})
public interface IMapaPerfilEstudianteDTO {

    @Mapping(source = "estudianteId", target = "estudiante.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "habilidades", ignore = true)
    PerfilEstudiante toEntity(PerfilEstudianteCreateDTO dto);

    @Mapping(source = "estudiante.id", target = "estudianteId")
    @Mapping(source = "habilidades", target = "habilidades", qualifiedByName = "mapHabilidadesToDTO")
    PerfilEstudianteDTO toDTO(PerfilEstudiante perfil);

    @Mapping(source = "estudianteId", target = "estudiante.id")
    @Mapping(target = "habilidades", ignore = true)
    PerfilEstudiante toEntity(PerfilEstudianteDTO dto);

    @Named("mapHabilidadesToDTO")
    default List<HabilidadDTO> mapHabilidadesToDTO(List<Habilidad> habilidades) {
        if (habilidades == null) return null;
        return habilidades.stream()
                .map(h -> new HabilidadDTO(
                        h.getId().intValue(),
                        h.getNombre(),
                        h.getNivel(),
                        h.getTipo()
                ))
                .collect(Collectors.toList());
    }
}
