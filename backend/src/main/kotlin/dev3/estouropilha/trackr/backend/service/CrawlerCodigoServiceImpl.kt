package dev3.estouropilha.trackr.backend.service

import dev3.estouropilha.trackr.backend.crawlers.correios.CorreiosCrawler
import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorCodigoEnum
import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorCodigoEnum.CORREIOS
import dev3.estouropilha.trackr.backend.models.Entrega
import org.springframework.stereotype.Service

@Service
class CrawlerCodigoServiceImpl(private val correiosCrawler: CorreiosCrawler) : CrawlerCodigoService {
    override fun consultarEntregasPorCodigoECrawler(codigoRastreio: String, tipoCrawlerPorCodigo: TipoCrawlerPorCodigoEnum): Entrega {
        when (tipoCrawlerPorCodigo) {
            CORREIOS -> return correiosCrawler.consultarEntregas(codigoRastreio)
        }
    }
}