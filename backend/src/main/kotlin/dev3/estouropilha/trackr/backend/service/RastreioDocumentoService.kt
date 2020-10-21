package dev3.estouropilha.trackr.backend.service

import dev3.estouropilha.trackr.backend.dto.RastreioDocumentoDto
import dev3.estouropilha.trackr.backend.dto.RastreioDto

interface RastreioDocumentoService {
    fun gravarVinculoRastreioDocumento(rastreioDocumentoDto: RastreioDocumentoDto)
    fun consultarRastreiosPorDocumento(documento: String): List<RastreioDto>
}