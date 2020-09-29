package dev3.estouropilha.trackr.backend.controllers

import dev3.estouropilha.trackr.backend.crawlers.ssw.SswCrawler
import dev3.estouropilha.trackr.backend.dto.EntregaDto
import dev3.estouropilha.trackr.backend.dto.MovimentacaoDto
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@Api("Entregas")
@RequestMapping("/entregas")
class EntregasController(private val sswCrawler: SswCrawler) {
    val CPF_REGEX = "^\\d{11}$"

    @GetMapping("/{cpf}")
    fun consultarPorCpf(@PathVariable("cpf")
                        cpf: String): ResponseEntity<List<EntregaDto>> {

        if (!Regex(CPF_REGEX).matches(cpf)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido")
        }

        val entregas = sswCrawler.consultarEntregas(cpf)
                .map {
                    EntregaDto(
                            it.movimentacoes.map { m -> MovimentacaoDto(m.data, m.detalhes, m.unidade) }
                    )
                }

        return ok(entregas)
    }
}