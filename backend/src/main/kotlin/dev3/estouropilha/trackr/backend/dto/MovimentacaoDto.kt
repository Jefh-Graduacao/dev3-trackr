package dev3.estouropilha.trackr.backend.dto

import io.swagger.annotations.ApiParam
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class MovimentacaoDto(
        @ApiParam(example = "2020-09-28")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        val dataHora: LocalDateTime,
        @ApiParam(example = "Centro de Distribuição XYZ")
        val local: String,
        @ApiParam(example = "Aguardando Separação")
        val situacao: String
)