package dev3.estouropilha.trackr.backend.controllers

import dev3.estouropilha.trackr.backend.dto.EntregaDto
import dev3.estouropilha.trackr.backend.dto.RastreioDocumentoDto
import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorCodigoEnum
import dev3.estouropilha.trackr.backend.mappers.EntregasMapper
import dev3.estouropilha.trackr.backend.service.EntregasService
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.created
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.lang.String.format
import java.net.URI

@RestController
@Api("Entregas")
@RequestMapping("/entregas")
class EntregasController(private val entregasService: EntregasService) {
    private val cpfRegex = "^\\d{11}$"

    @GetMapping("/{cpf}")
    @CrossOrigin(origins = ["http://localhost:8081", "https://trackr.wtf"])
    fun consultarPorCpf(@PathVariable("cpf") cpf: String): ResponseEntity<List<EntregaDto>> {

        if (!Regex(cpfRegex).matches(cpf)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido")
        }
        return ok(entregasService.consultarEntregasPorCpf(cpf)
                .map {
                    EntregasMapper.map(it, cpf, "")
                }
        )
    }

    @PostMapping
    @CrossOrigin(origins = ["http://localhost:8081", "https://trackr.wtf"])
    fun vincularCodigoDeRastreioADocumento(@RequestBody rastreioDocumentoDto: RastreioDocumentoDto): ResponseEntity<List<EntregaDto>> {
        entregasService.gravarVinculoRastreioDocumento(rastreioDocumentoDto)
        return created(URI.create(format("/entregas/%s", rastreioDocumentoDto.numeroDocumento))).build()
    }

    @GetMapping("/correios/{codigoRastreio}/")
    @CrossOrigin(origins = ["http://localhost:8081", "https://trackr.wtf"])
    fun consultarCorreios(@PathVariable codigoRastreio: String): ResponseEntity<EntregaDto> {
        return ok(EntregasMapper.map(
                entregasService.consultarEntregasPorCodigoRastreioEOrigem(codigoRastreio, TipoCrawlerPorCodigoEnum.CORREIOS),
                "",
                codigoRastreio)
        )
    }
}