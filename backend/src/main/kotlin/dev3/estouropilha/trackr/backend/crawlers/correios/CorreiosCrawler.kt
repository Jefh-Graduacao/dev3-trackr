package dev3.estouropilha.trackr.backend.crawlers.correios

import com.google.common.collect.Lists.newArrayList
import dev3.estouropilha.trackr.backend.crawlers.CrawlerCodigo
import dev3.estouropilha.trackr.backend.models.Entrega
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class CorreiosCrawler : CrawlerCodigo {
    @Value("\${ssw.url}")
    private lateinit var baseUrl: String

    override fun consultarEntregas(cpfDestinatario: String): Entrega {
        return Entrega(newArrayList())
    }
}