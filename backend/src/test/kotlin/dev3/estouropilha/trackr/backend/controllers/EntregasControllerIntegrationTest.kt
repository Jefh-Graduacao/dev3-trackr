package dev3.estouropilha.trackr.backend.controllers

import dev3.estouropilha.trackr.backend.dto.RastreioDocumentoDto
import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorCodigoEnum.CORREIOS
import dev3.estouropilha.trackr.backend.helpers.BaseIntegrationTest
import dev3.estouropilha.trackr.backend.helpers.INSERIR_RASTREIO_DOCUMENTO
import dev3.estouropilha.trackr.backend.helpers.LIMPAR_TABELAS
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Sql(scripts = [LIMPAR_TABELAS], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
internal class EntregasControllerIntegrationTest : BaseIntegrationTest() {
    @Test
    fun `Informar CPF invalido deve retornar BadRequest`() {
        super.getMockMvc()
                .perform(get("/entregas/{cpf}", "123456789"))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun `Informar CPF valido deve retornar entregas existentes`() {
        getMockMvc()
                .perform(get("/entregas/{cpf}", "12345678999"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[ { \"movimentacoes\": [ { \"dataHora\": \"2020-09-16T01:39:00\", \"situacao\": \"Redespacho autorizado com 1 volume e 0 Kg. Destino: RS/CACHOEIRINHA. Previsao de entrega: 18/09/20.\", \"local\": \"CONTAGEM / MG RED CON\" }, { \"dataHora\": \"2020-09-16T04:27:00\", \"situacao\": \"Saida da unidade CONTAGEM em 16/09/20, 04:27h. Previsao de chegada na unidade JUNDIAI em 16/09/20, 14:26h.\", \"local\": \"CONTAGEM / MG RED CON\" }, { \"dataHora\": \"2020-09-16T14:20:00\", \"situacao\": \"Chegada na unidade JUNDIAI em 16/09/20, 14:20h.\", \"local\": \"JUNDIAI / SP RED SPO\" }, { \"dataHora\": \"2020-09-17T01:41:00\", \"situacao\": \"Saida da unidade JUNDIAI em 17/09/20, 01:41h. Previsao de chegada na unidade GRAVATAI em 17/09/20, 21:39h.\", \"local\": \"JUNDIAI / SP RED SPO\" }, { \"dataHora\": \"2020-09-17T22:24:00\", \"situacao\": \"Chegada na unidade GRAVATAI em 17/09/20, 22:24h.\", \"local\": \"GRAVATAI / RS RED POT\" }, { \"dataHora\": \"2020-09-18T09:42:00\", \"situacao\": \"Saida para entrega na cidade CACHOEIRINHA.\", \"local\": \"GRAVATAI / RS RED POT\" }, { \"dataHora\": \"2020-09-18T13:18:00\", \"situacao\": \"ENTREGUE EM 18/09/20 13:18H RECEBIDO POR DENIS RENATO, PORTEIRO DO DESTINATARIO - INFORMADA ATRAVES DA CHAVE **********************0020009606991082023928 - MOBILE 5 DOC N.: *****70264.\", \"local\": \"GRAVATAI / RS RED POT\" } ] } ]"))
    }

    @Test
    fun `Deve vincular codigo de rastreio ao CPF retornando Created`() {
        val rastreioDocumentoDto = RastreioDocumentoDto("11111111111", "1234-ABC", CORREIOS)
        getMockMvc()
                .perform(post("/entregas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(rastreioDocumentoDto)))
                .andExpect(status().isCreated)
    }

    @Test
    @Sql(scripts = [INSERIR_RASTREIO_DOCUMENTO], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    fun `Deve vincular codigo de rastreio a CPF que já possuiu outros vinculos retornando Created`() {
        val rastreioDocumentoDto = RastreioDocumentoDto("11111111111", "1234-ABC", CORREIOS)
        getMockMvc()
                .perform(post("/entregas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(rastreioDocumentoDto)))
                .andExpect(status().isCreated)
    }
}