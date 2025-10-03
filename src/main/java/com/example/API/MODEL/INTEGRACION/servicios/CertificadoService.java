package com.example.API.MODEL.INTEGRACION.servicios;

import com.example.API.MODEL.INTEGRACION.excepciones.RecursoNoEncontradoException;
import com.example.API.MODEL.INTEGRACION.modelos.Certificado;
import com.example.API.MODEL.INTEGRACION.modelos.PerfilEstudiante;
import com.example.API.MODEL.INTEGRACION.modelos.dtos.CertificadoDTO;
import com.example.API.MODEL.INTEGRACION.modelos.mapas.IMapaCertificadoDTO;
import com.example.API.MODEL.INTEGRACION.repositorio.ICertificadoRepository;
import com.example.API.MODEL.INTEGRACION.repositorio.IPerfilEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificadoService {

    @Autowired
    private ICertificadoRepository certificadoRepository;

    @Autowired
    private IPerfilEstudianteRepository perfilRepository;

    @Autowired
    private IMapaCertificadoDTO certificadoMapper;

    // Crear certificado
    public CertificadoDTO crearCertificado(CertificadoDTO dto, Long perfilEstudianteId) {
        PerfilEstudiante perfil = perfilRepository.findById(perfilEstudianteId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Perfil de estudiante con id " + perfilEstudianteId + " no encontrado"
                ));

        Certificado certificado = new Certificado(
                null,
                dto.getNombre(),
                dto.getInstitucion(),
                dto.getFecha(),
                dto.getUrlArchivo(),
                perfil
        );

        Certificado guardado = certificadoRepository.save(certificado);
        return certificadoMapper.toDTO(guardado);
    }

    // Listar todos los certificados
    public List<CertificadoDTO> listarCertificados() {
        return certificadoRepository.findAll()
                .stream()
                .map(certificadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Listar certificados por perfil
    public List<CertificadoDTO> listarCertificadosPorPerfil(Long perfilEstudianteId) {
        return certificadoRepository.findByPerfilEstudiante_Id(perfilEstudianteId)
                .stream()
                .map(certificadoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
