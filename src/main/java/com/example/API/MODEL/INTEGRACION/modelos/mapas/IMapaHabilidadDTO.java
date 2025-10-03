package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.Habilidad;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.HabilidadDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMapaHabilidadDTO {

    // De entidad a DTO
    @Mapping(source = "perfilEstudiante.id", target = "perfilEstudianteId")
    HabilidadDTO toDTO(Habilidad habilidad);

    // De DTO a entidad
    @Mapping(source = "perfilEstudianteId", target = "perfilEstudiante.id")
    Habilidad toEntity(HabilidadDTO dto);
}

