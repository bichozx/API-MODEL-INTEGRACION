package com.example.API.MODEL.INTEGRACION.modelos.mapas;

import com.example.API.MODEL.INTEGRACION.modelos.Certificado;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.CertificadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMapaCertificadoDTO {

    // De entidad a DTO
    @Mapping(source = "perfilEstudiante.id", target = "perfilEstudianteId")
    CertificadoDTO toDTO(Certificado certificado);

    // De DTO a entidad
    @Mapping(source = "perfilEstudianteId", target = "perfilEstudiante.id")
    Certificado toEntity(CertificadoDTO dto);
}

