package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.Usuario;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.UsuarioCreateDTO;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IMapaUsuarioDTO {

    // De entidad a DTO
    UsuarioDTO toDTO(Usuario usuario);

    // De DTO a entidad
    Usuario toEntity(UsuarioDTO usuarioDTO);

    // De DTO de creación a entidad
    Usuario toEntity(UsuarioCreateDTO usuarioCreateDTO);
}

