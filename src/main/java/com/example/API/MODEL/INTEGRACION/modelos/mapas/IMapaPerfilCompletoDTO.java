package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.PerfilEstudiante;
import com.example.API.MODEL.INTEGRACION.modelos.Proyecto;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.PerfilEstudianteCompletoDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.ProyectoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {IMapaHabilidadDTO.class, IMapaProyectoDTO.class, IMapaCertificadoDTO.class})
public interface IMapaPerfilCompletoDTO {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "resumen", target = "resumen")
    @Mapping(source = "intereses", target = "intereses")
    @Mapping(source = "experiencia", target = "experiencia")
    // 🔴 Se elimina el mapeo directo de proyectos -> String
    // @Mapping(source = "proyectos", target = "proyectos")

    @Mapping(source = "estudiante.id", target = "estudianteId")
    @Mapping(source = "habilidades", target = "habilidades")
    @Mapping(source = "proyectos", target = "proyectosDetalle", qualifiedByName = "mapProyectos")
    @Mapping(source = "proyectos", target = "proyectos", qualifiedByName = "mapProyectosAsString") // ✅ ahora sí el string
    @Mapping(source = "certificados", target = "certificados")
    PerfilEstudianteCompletoDTO toCompletoDTO(PerfilEstudiante perfil);

    // Convierte lista de proyectos a lista de ProyectoDTO
    @Named("mapProyectos")
    default List<ProyectoDTO> mapProyectos(List<Proyecto> proyectos) {
        if (proyectos == null) return null;
        return proyectos.stream()
                .map(p -> new ProyectoDTO(
                        p.getId() != null ? p.getId().intValue() : null,
                        p.getTitulo(),
                        p.getDescripcion(),
                        p.getUrl(),
                        p.getTecnologias(),
                        p.getPerfilEstudiante() != null ?
                                (p.getPerfilEstudiante().getId() != null ? p.getPerfilEstudiante().getId().intValue() : null)
                                : null
                ))
                .collect(Collectors.toList());
    }

    // Convierte lista de proyectos en un String (concatenado)
    @Named("mapProyectosAsString")
    default String mapProyectosAsString(List<Proyecto> proyectos) {  // ✅ aquí debe ser List<Proyecto>
        if (proyectos == null || proyectos.isEmpty()) return null;
        return proyectos.stream()
                .map(Proyecto::getTitulo)  // ✅ funciona con entidad
                .collect(Collectors.joining(", "));
    }
}
