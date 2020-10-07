package dev3.estouropilha.trackr.backend.crawlers

import dev3.estouropilha.trackr.backend.models.Entrega

interface CrawlerDocumento {
    fun consultarEntregas(cpfDestinatario: String): List<Entrega>
}