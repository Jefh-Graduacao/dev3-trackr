package dev3.estouropilha.trackr.backend.repository

import dev3.estouropilha.trackr.backend.models.RastreioDocumento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RastreioDocumentoRepository : JpaRepository<RastreioDocumento, Long> {
    fun findAllByNumeroDocumento(numeroDocumento: String) : List<RastreioDocumento>
}