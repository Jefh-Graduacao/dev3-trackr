package dev3.estouropilha.trackr.backend.mappers

import dev3.estouropilha.trackr.backend.dto.EntregaDto
import dev3.estouropilha.trackr.backend.dto.MovimentacaoDto
import dev3.estouropilha.trackr.backend.models.Entrega
import dev3.estouropilha.trackr.backend.models.Movimentacao

class EntregasMapper {
    companion object {
        fun map(entrega: Entrega, cpf: String, codigo: String) : EntregaDto =
                EntregaDto(cpf,
                        entrega.movimentacoes
                                .sortedByDescending { mov -> mov.data }
                                .map { map(it) },
                        codigo)

        private fun map(movimentacao: Movimentacao) =
                MovimentacaoDto(movimentacao.titulo, movimentacao.data, movimentacao.unidade, movimentacao.detalhes)
    }
}