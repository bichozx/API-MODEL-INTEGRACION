package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.Familiar;
import com.example.API.MODEL.INTEGRACION.modelos.Usuario;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.FamiliarCreateDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.FamiliarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { IMapaUsuarioDTO.class })
public interface IMapaFamiliarDTO {

    // Mapeo de entidad a DTO completo
    FamiliarDTO toDTO(Familiar familiar);

    // Mapeo de DTO completo a entidad
    Familiar toEntity(FamiliarDTO familiarDTO);

    // Mapeo de DTO de creación a entidad
    @Mapping(target = "id", ignore = true) // El ID se genera automáticamente
    @Mapping(target = "usuario", source = "usuarioId") // MapStruct usará el método auxiliar
    @Mapping(target = "direccion", source = "direccion") // Mapear dirección si existe
    @Mapping(target = "telefono", source = "telefono")
    @Mapping(target = "parentesco", source = "parentesco")
    Familiar toEntity(FamiliarCreateDTO dto);

    // Método auxiliar que MapStruct no puede inferir automáticamente
    default Usuario map(Long usuarioId) {
        if (usuarioId == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        return usuario;
    }
}
