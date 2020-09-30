package dev3.estouropilha.trackr.backend.dto

import io.swagger.annotations.ApiModelProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class MovimentacaoDto(
        @ApiModelProperty(example = "2020-09-28T21:01:30")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        val dataHora: LocalDateTime,
        @ApiModelProperty(example = "Centro de Distribuição XYZ")
        val local: String,
        @ApiModelProperty(example = "Aguardando Separação")
        val situacao: String
)