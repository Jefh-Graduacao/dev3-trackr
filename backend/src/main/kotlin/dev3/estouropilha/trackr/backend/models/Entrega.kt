package dev3.estouropilha.trackr.backend.models

data class Entrega(
        val movimentacoes: List<Movimentacao>,
        val origem: String
)