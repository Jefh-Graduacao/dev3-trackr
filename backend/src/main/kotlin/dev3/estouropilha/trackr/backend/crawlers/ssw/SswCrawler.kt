package dev3.estouropilha.trackr.backend.crawlers.ssw

import dev3.estouropilha.trackr.backend.crawlers.CrawlerDocumento
import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorDocumentoEnum
import dev3.estouropilha.trackr.backend.models.Entrega
import dev3.estouropilha.trackr.backend.models.Movimentacao
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class SswCrawler : CrawlerDocumento {
    @Value("\${ssw.url}")
    private lateinit var baseUrl: String

    override fun consultarEntregas(cpfDestinatario: String): List<Entrega> {
        return Jsoup.connect("${baseUrl}/2/resultSSW_dest")
                .data("urlori", "/2/rastreamento_pf")
                .data("cnpjdest", cpfDestinatario)
                .post()
                .select("a.email")
                .map {
                    val onclickAttr = it.attr("onClick")

                    val detalhes = Jsoup.connect("${baseUrl}${onclickAttr.substring(onclickAttr.indexOf("'") + 1, onclickAttr.lastIndexOf("'"))}")
                            .get()
                            .select("table")[1]
                            .select("tr")
                            .drop(1)
                            .filter { tr ->
                                tr.children().any { c -> c.hasClass("rastreamento") } && tr.children().count() >= 3
                            }
                            .map { linha ->
                                val (data, unidade, detalhes) = linha.select("td").map { td ->
                                    td.select("p.tdb").text()
                                }

                                Movimentacao(
                                        linha.select("td")[2].select("p.titulo").text() ?: "",
                                        LocalDateTime.parse(data, DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")),
                                        unidade,
                                        detalhes
                                )
                            }
                    Entrega(detalhes, "CPF")
                }
    }
}