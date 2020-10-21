package dev3.estouropilha.trackr.backend.models

import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorCodigoEnum
import javax.persistence.*

@Table
@Entity
data class RastreioDocumento(
        @field:Id
        @field:Column
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        val idRastreioDocumento: Long? = null,
        @field:Column
        val numeroDocumento: String = "",
        @field:Column
        val codigoRastreio: String = "",
        @field:Column
        @field:Enumerated(EnumType.STRING)
        val origem: TipoCrawlerPorCodigoEnum = TipoCrawlerPorCodigoEnum.CORREIOS) {

}