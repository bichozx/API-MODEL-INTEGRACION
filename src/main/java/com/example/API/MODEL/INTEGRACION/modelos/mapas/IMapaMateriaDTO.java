package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.Materia;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.MateriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaMateriaDTO {

    @Mappings({
            @Mapping(source = "docente.usuario.nombre", target = "docenteNombre")
    })
    MateriaDTO toDTO(Materia materia);

    List<MateriaDTO> toDTOList(List<Materia> materias);
}

