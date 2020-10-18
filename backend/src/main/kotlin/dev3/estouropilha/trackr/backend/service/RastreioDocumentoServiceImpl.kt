package dev3.estouropilha.trackr.backend.service

import dev3.estouropilha.trackr.backend.dto.RastreioDocumentoDto
import dev3.estouropilha.trackr.backend.dto.RastreioDto
import dev3.estouropilha.trackr.backend.models.RastreioDocumento
import dev3.estouropilha.trackr.backend.repository.RastreioDocumentoRepository

class RastreioDocumentoServiceImpl(val rastreioDocumentoRepository: RastreioDocumentoRepository)
    : RastreioDocumentoService {

    override fun gravarVinculoRastreioDocumento(rastreioDocumentoDto: RastreioDocumentoDto) {
        rastreioDocumentoRepository.save(
                RastreioDocumento(rastreioDocumentoDto.numeroDocumento,
                        rastreioDocumentoDto.codigoRastreio, rastreioDocumentoDto.origem))
    }

    override fun consultarRastreiosPorDocumento(documento: String): List<RastreioDto> {
        return rastreioDocumentoRepository.findAllByNumeroDocumento(documento)
                .map { RastreioDto(it.codigoRastreio, it.origem) }
    }

}