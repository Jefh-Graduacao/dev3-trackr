package dev3.estouropilha.trackr.backend.crawlers.correios

import dev3.estouropilha.trackr.backend.crawlers.CrawlerCodigo
import dev3.estouropilha.trackr.backend.models.Entrega
import dev3.estouropilha.trackr.backend.models.Movimentacao
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class CorreiosCrawler : CrawlerCodigo {
    @Value("\${ssw.url}")
    private lateinit var baseUrl: String

    override fun consultarEntrega(codigoRastreio: String): Entrega {
        val movimentacoes = Jsoup.connect("https://www2.correios.com.br/sistemas/rastreamento/ctrl/ctrlRastreamento.cfm")
                .data("acao", "track")
                .data("objetos", codigoRastreio)
                .data("btnPesq", "Buscar")
                .followRedirects(true)
                .post()
                .select("table.listEvent.sro")
                .map { tabela ->
                    val (data, local) =
                            tabela.select(".sroDtEvent")
                                    .text()
                                    .split("""(?<=\d{2}\/\d{2}\/\d{4} \d{2}:\d{2})""".toRegex())
                                    .map { it.trim() }
                                    .filter { !it.isBlank() }

                    val lbEvent = tabela.select(".sroLbEvent")

                    Movimentacao(
                            lbEvent.select("strong").text(),
                            LocalDateTime.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                            local,
                            Regex("(?<=<br>)[\\s\\S]*").find(lbEvent.html())?.groupValues?.first()?.trim() ?: ""
                    )
                }

        return Entrega(movimentacoes)
    }
}