package dev3.estouropilha.trackr.backend.controllers

import dev3.estouropilha.trackr.backend.crawlers.CorreiosCrawler
import dev3.estouropilha.trackr.backend.crawlers.SswCrawler
import dev3.estouropilha.trackr.backend.dto.EntregaDto
import dev3.estouropilha.trackr.backend.dto.MovimentacaoDto
import dev3.estouropilha.trackr.backend.mappers.EntregasMapper
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@Api("Entregas")
@RequestMapping("/entregas")
class EntregasController(
        private val sswCrawler: SswCrawler,
        private val correiosCrawler: CorreiosCrawler
) {
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
                    EntregasMapper.map(it, cpf, "")
                }
        )
    }

    @GetMapping("/correios/{codigoRastreio}")
    @CrossOrigin(origins = ["http://localhost:8081", "https://trackr.wtf"])
    fun consultarCorreios(@PathVariable("codigoRastreio") codigoRastreio: String): EntregaDto =
            EntregasMapper.map(
                    correiosCrawler.consultarEntrega(codigoRastreio),
                    "",
                    codigoRastreio
            )
}