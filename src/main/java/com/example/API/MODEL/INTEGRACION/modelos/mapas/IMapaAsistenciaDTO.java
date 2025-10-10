package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.Asistencia;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.AsistenciaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMapaAsistenciaDTO {

    @Mapping(target = "nombreEstudiante", source = "estudiante.usuario.nombre")
    @Mapping(target = "nombreGrupo", source = "grupo.nombre")
    AsistenciaDTO toDTO(Asistencia asistencia);
}

