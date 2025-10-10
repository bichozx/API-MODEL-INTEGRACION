package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.Asistencia;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.AsistenciaSimpleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaAsistenciaSimpleDTO {

    // 🔹 Ruta completa hacia el nombre del estudiante
    @Mapping(source = "estudiante.usuario.nombre", target = "estudiante")
    @Mapping(source = "grupo.nombre", target = "grupo")
    AsistenciaSimpleDTO toDTO(Asistencia asistencia);

    List<AsistenciaSimpleDTO> toDTOList(List<Asistencia> asistencias);
}
