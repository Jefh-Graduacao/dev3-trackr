package dev3.estouropilha.trackr.backend.dto

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.annotations.ApiModelProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class EntregaDto(
        @field:ApiModelProperty(example = "12345678922")
        val cpf: String,
        val movimentacoes: List<MovimentacaoDto>,
        @field:ApiModelProperty(example = "ABCXD-1234")
        val codigo: String,
        @field:ApiModelProperty(example = "CORREIOS")
        val origem: String
)
