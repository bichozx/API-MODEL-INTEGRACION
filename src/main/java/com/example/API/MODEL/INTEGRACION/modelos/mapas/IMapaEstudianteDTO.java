package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.Estudiante;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.EstudianteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { IMapaUsuarioDTO.class })
public interface IMapaEstudianteDTO {

    // Entidad -> DTO
    EstudianteDTO toDTO(Estudiante estudiante);

    // DTO -> Entidad
    Estudiante toEntity(EstudianteDTO estudianteDTO);
}

