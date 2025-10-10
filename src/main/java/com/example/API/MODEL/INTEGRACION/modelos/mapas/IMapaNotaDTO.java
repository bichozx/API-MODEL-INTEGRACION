package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.Nota;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.NotaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaNotaDTO {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "materia.nombre", target = "materia"),
            @Mapping(source = "calificacion", target = "calificacion"),
            @Mapping(source = "fecha", target = "fecha"),
            @Mapping(source = "tipoEvaluacion", target = "tipoEvaluacion")
    })
    NotaDTO toDTO(Nota nota);

    List<NotaDTO> toDTOList(List<Nota> notas);
}
