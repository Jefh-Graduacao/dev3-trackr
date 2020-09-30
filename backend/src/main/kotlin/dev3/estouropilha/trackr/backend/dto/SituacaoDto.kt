package dev3.estouropilha.trackr.backend.dto

import io.swagger.annotations.ApiModelProperty

data class SituacaoDto(
        @ApiModelProperty(example = "Mouse XYZ")
        val titulo: String,
        @ApiModelProperty(example = "Mouse Gamer - XYZ")
        val descricao: String
)