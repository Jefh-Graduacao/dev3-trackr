package dev3.estouropilha.trackr.backend.service

import dev3.estouropilha.trackr.backend.models.Entrega

interface CrawlerDocumentoService {
    fun consultarEntregasPorCpf(cpfDestinatario: String): List<Entrega>
}