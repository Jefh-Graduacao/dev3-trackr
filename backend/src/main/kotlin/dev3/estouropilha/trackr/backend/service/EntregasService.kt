package dev3.estouropilha.trackr.backend.service

import dev3.estouropilha.trackr.backend.dto.RastreioDocumentoDto
import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorCodigoEnum
import dev3.estouropilha.trackr.backend.models.Entrega

interface EntregasService {
    fun consultarEntregasPorCpf(cpfDestinatario: String): List<Entrega>
    fun consultarEntregasPorCodigoRastreioEOrigem(codigoRastreio: String, origem: TipoCrawlerPorCodigoEnum): Entrega
    fun gravarVinculoRastreioDocumento(rastreioDocumentoDto: RastreioDocumentoDto)
}