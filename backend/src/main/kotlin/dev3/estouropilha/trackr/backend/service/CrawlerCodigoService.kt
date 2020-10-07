package dev3.estouropilha.trackr.backend.service

import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorCodigoEnum
import dev3.estouropilha.trackr.backend.models.Entrega

interface CrawlerCodigoService {
    fun consultarEntregasPorCodigoECrawler(codigoRastreio: String, tipoCrawlerPorCodigoEnum: TipoCrawlerPorCodigoEnum)
            : Entrega
}