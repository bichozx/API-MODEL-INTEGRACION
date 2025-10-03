package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.VinculoFamiliarEstudiante;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.VinculoFamiliarEstudianteCreateDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.VinculoFamiliarEstudianteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMapaVinculoFamiliarEstudianteDTO {

    // De entidad a DTO
    @Mapping(source = "familiar.id", target = "familiarId")
    @Mapping(source = "estudiante.id", target = "estudianteId")
    VinculoFamiliarEstudianteDTO toDTO(VinculoFamiliarEstudiante vinculo);

    // De DTO de creación a entidad (rellenar familiar y estudiante en el servicio)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "familiar", ignore = true)
    @Mapping(target = "estudiante", ignore = true)
    VinculoFamiliarEstudiante toEntity(VinculoFamiliarEstudianteCreateDTO dto);
}

