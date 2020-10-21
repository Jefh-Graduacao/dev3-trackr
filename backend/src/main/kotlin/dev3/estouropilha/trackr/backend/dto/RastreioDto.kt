package dev3.estouropilha.trackr.backend.dto

import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorCodigoEnum

data class RastreioDto(val codigoRastreio: String,
                       val origem: TipoCrawlerPorCodigoEnum)