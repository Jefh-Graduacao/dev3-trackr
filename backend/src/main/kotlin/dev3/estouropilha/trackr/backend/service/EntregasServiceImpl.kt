package dev3.estouropilha.trackr.backend.service

import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorCodigoEnum
import org.springframework.stereotype.Service

@Service
class EntregasServiceImpl(private val crawlerDocumentoService: CrawlerDocumentoService,
                          private val crawlerCodigoService: CrawlerCodigoService) : EntregasService {

    override fun consultarEntregasPorCpf(cpfDestinatario: String) = crawlerDocumentoService.consultarEntregasPorCpf(cpfDestinatario)

    override fun consultarEntregasPorCodigoRastreioEOrigem(codigoRastreio: String, origem: TipoCrawlerPorCodigoEnum) = crawlerCodigoService.consultarEntregasPorCodigoECrawler(codigoRastreio, origem)
}