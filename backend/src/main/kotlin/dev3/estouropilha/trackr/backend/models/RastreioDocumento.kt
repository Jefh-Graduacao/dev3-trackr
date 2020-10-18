package dev3.estouropilha.trackr.backend.models

import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorCodigoEnum
import javax.persistence.*

@Table
@Entity
data class RastreioDocumento(
        @field:Id
        @field:Column
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        val idRastreioDocumento: Long,
        @field:Column
        var numeroDocumento: String,
        @field:Column
        var codigoRastreio: String,
        @field:Column
        @field:Enumerated(EnumType.STRING)
        var origem: TipoCrawlerPorCodigoEnum) {

    constructor(numeroDocumento: String, codigoRastreio: String, origem: TipoCrawlerPorCodigoEnum) {
        this.numeroDocumento = numeroDocumento
        this.codigoRastreio = codigoRastreio
        this.origem = origem
    }
}