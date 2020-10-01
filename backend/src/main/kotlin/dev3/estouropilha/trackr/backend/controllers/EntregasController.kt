package dev3.estouropilha.trackr.backend.controllers

import dev3.estouropilha.trackr.backend.crawlers.ssw.SswCrawler
import dev3.estouropilha.trackr.backend.dto.EntregaDto
import dev3.estouropilha.trackr.backend.dto.MovimentacaoDto
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@Api("Entregas")
@RequestMapping("/entregas")
class EntregasController(private val sswCrawler: SswCrawler) {
    private val cpfRegex = "^\\d{11}$"

    @GetMapping("/{cpf}")
    @CrossOrigin(origins = ["http://localhost:8081", "https://trackr.wtf"])
    fun consultarPorCpf(@PathVariable("cpf")
                        cpf: String): ResponseEntity<List<EntregaDto>> {

        if (!Regex(cpfRegex).matches(cpf)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido")
        }
        return ok(sswCrawler.consultarEntregas(cpf)
                .map {
                    EntregaDto(cpf,
                            it.movimentacoes
                                    .sortedByDescending { mov -> mov.data }
                                    .map { movimentacao ->
                                        MovimentacaoDto(movimentacao.titulo, movimentacao.data, movimentacao.unidade, movimentacao.detalhes)
                                    }, "código")
                }
        )
    }
}