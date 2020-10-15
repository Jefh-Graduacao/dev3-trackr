package dev3.estouropilha.trackr.backend.service

import dev3.estouropilha.trackr.backend.crawlers.CrawlerDocumento
import dev3.estouropilha.trackr.backend.crawlers.ssw.SswCrawler
import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorDocumentoEnum
import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorDocumentoEnum.SSW
import dev3.estouropilha.trackr.backend.models.Entrega
import org.springframework.stereotype.Service

@Service
class CrawlerDocumentoServiceImpl(private val sswCrawler: SswCrawler) : CrawlerDocumentoService {
    override fun consultarEntregasPorCpf(cpfDestinatario: String): List<Entrega> {
        return enumValues<TipoCrawlerPorDocumentoEnum>().map {
            getServiceByTipoCrawlerEnum(it).consultarEntregas(cpfDestinatario)
        }.flatten()
    }

    private fun getServiceByTipoCrawlerEnum(tipoCrawlerPorDocumentoEnum: TipoCrawlerPorDocumentoEnum): CrawlerDocumento {
        when (tipoCrawlerPorDocumentoEnum) {
            SSW -> return sswCrawler
        }
    }
}