package dev3.estouropilha.trackr.backend.dto

import io.swagger.annotations.ApiParam

data class SituacaoDto(
        @ApiParam(example = "Mouse XYZ")
        val titulo: String,
        @ApiParam(example = "Mouse Gamer - XYZ")
        val descricao: String = ""
)