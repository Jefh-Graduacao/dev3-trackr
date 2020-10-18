package dev3.estouropilha.trackr.backend.service

import dev3.estouropilha.trackr.backend.dto.RastreioDocumentoDto
import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorCodigoEnum
import dev3.estouropilha.trackr.backend.models.Entrega
import org.apache.commons.collections4.ListUtils
import org.springframework.stereotype.Service

@Service
class EntregasServiceImpl(private val crawlerDocumentoService: CrawlerDocumentoService,
                          private val crawlerCodigoService: CrawlerCodigoService,
                          private val rastreioDocumentoService: RastreioDocumentoService) : EntregasService {

    override fun consultarEntregasPorCpf(cpfDestinatario: String): List<Entrega> {
        val entregasVinculadasAoDocumento = rastreioDocumentoService.consultarRastreiosPorDocumento(cpfDestinatario)
                .map { consultarEntregasPorCodigoRastreioEOrigem(it.codigoRastreio, it.origem) }

        return ListUtils.union(crawlerDocumentoService.consultarEntregasPorCpf(cpfDestinatario),
                entregasVinculadasAoDocumento)

    }

    override fun consultarEntregasPorCodigoRastreioEOrigem(codigoRastreio: String, origem: TipoCrawlerPorCodigoEnum): Entrega {
        return crawlerCodigoService.consultarEntregasPorCodigoECrawler(codigoRastreio, origem)
    }

    override fun gravarVinculoRastreioDocumento(rastreioDocumentoDto: RastreioDocumentoDto) {
        rastreioDocumentoService.gravarVinculoRastreioDocumento(rastreioDocumentoDto)
    }
}