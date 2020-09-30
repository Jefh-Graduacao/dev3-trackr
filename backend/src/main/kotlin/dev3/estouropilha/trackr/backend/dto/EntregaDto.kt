package dev3.estouropilha.trackr.backend.dto

data class EntregaDto(
        val cpf: String,
        val movimentacoes: Iterable<MovimentacaoDto>,
        val codigo: String
)

