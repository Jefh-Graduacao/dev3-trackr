package dev3.estouropilha.trackr.backend.controllers

import dev3.estouropilha.trackr.backend.dto.EntregaDto
import dev3.estouropilha.trackr.backend.dto.MovimentacaoDto
import dev3.estouropilha.trackr.backend.service.EntregasService
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@Api("Entregas")
@RequestMapping("/entregas")
class EntregasController(private val entregasService: EntregasService) {
    private val cpfRegex = "^\\d{11}$"

    @GetMapping("/{cpf}")
    @CrossOrigin(origins = ["http://localhost:8081"])
    fun consultarPorCpf(@PathVariable("cpf")
                        cpf: String): ResponseEntity<List<EntregaDto>> {

        if (!Regex(cpfRegex).matches(cpf)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido")
        }
        return ok(entregasService.consultarEntregasPorCpf(cpf)
                .map {
                    EntregaDto(cpf,
                            it.movimentacoes
                            .map { movimentacao ->
                                MovimentacaoDto(movimentacao.data, movimentacao.detalhes, movimentacao.unidade)
                            }, "código")
                }
        )
    }
}