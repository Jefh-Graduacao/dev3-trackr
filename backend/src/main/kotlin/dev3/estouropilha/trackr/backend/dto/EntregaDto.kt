package dev3.estouropilha.trackr.backend.dto

import io.swagger.annotations.ApiModelProperty

data class EntregaDto(
        @field:ApiModelProperty(example = "11111111111")
        val cpf: String,
        val movimentacoes: List<MovimentacaoDto>,
        @field:ApiModelProperty(example = "A1Z2")
        val codigo: String
)

