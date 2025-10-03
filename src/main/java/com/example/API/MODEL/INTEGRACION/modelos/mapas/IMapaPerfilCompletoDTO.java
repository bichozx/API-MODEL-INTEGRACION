package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.PerfilEstudiante;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.PerfilEstudianteCompletoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {IMapaHabilidadDTO.class, IMapaProyectoDTO.class, IMapaCertificadoDTO.class})
public interface IMapaPerfilCompletoDTO {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "resumen", target = "resumen")
    @Mapping(source = "intereses", target = "intereses")
    @Mapping(source = "experiencia", target = "experiencia")
    @Mapping(source = "estudiante.id", target = "estudianteId")

    // ✅ MapStruct usará automáticamente IMapaProyectoDTO.toDTO() para cada elemento
    @Mapping(source = "proyectos", target = "proyectos")
    @Mapping(source = "proyectos", target = "proyectosDetalle")

    // ✅ Estos usarán sus respectivos mappers automáticamente
    @Mapping(source = "habilidades", target = "habilidades")
    @Mapping(source = "certificados", target = "certificados")
    PerfilEstudianteCompletoDTO toCompletoDTO(PerfilEstudiante perfil);
}