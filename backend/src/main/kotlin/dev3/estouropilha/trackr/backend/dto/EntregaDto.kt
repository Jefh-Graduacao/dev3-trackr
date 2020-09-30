package dev3.estouropilha.trackr.backend.dto

import io.swagger.annotations.ApiModelProperty

data class EntregaDto(
        @field:ApiModelProperty(example = "12345678922")
        val cpf: String,
        val movimentacoes: List<MovimentacaoDto>,
        @field:ApiModelProperty(example = "ABCXD-1234")
        val codigo: String
)

