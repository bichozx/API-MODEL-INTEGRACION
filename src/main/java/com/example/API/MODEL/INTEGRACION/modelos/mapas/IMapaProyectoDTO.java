package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.Proyecto;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.ProyectoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMapaProyectoDTO {

    // De entidad a DTO
    @Mapping(source = "perfilEstudiante.id", target = "perfilEstudianteId")
    ProyectoDTO toDTO(Proyecto proyecto);

    // De DTO a entidad
    @Mapping(source = "perfilEstudianteId", target = "perfilEstudiante.id")
    Proyecto toEntity(ProyectoDTO dto);
}

