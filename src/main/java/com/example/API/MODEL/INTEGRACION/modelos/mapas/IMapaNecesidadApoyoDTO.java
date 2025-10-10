package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.NecesidadApoyo;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.NecesidadApoyoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMapaNecesidadApoyoDTO {
    NecesidadApoyo toEntity(NecesidadApoyoDTO dto);
    NecesidadApoyoDTO toDTO(NecesidadApoyo entity);
}
