package dev3.estouropilha.trackr.backend.dto

import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorCodigoEnum

data class RastreioDocumentoDto(val numeroDocumento: String,
                                val codigoRastreio: String,
                                val origem: TipoCrawlerPorCodigoEnum) {
}