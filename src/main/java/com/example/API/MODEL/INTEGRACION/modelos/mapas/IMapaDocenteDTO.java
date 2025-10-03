package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.Docente;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.DocenteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { IMapaUsuarioDTO.class })
public interface IMapaDocenteDTO {
    DocenteDTO toDTO(Docente docente);
    Docente toEntity(DocenteDTO docenteDTO);
}

