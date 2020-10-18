package dev3.estouropilha.trackr.backend.controllers

import dev3.estouropilha.trackr.backend.crawlers.correios.CorreiosCrawler
import dev3.estouropilha.trackr.backend.dto.EntregaDto
import dev3.estouropilha.trackr.backend.dto.MovimentacaoDto
import dev3.estouropilha.trackr.backend.dto.RastreioDocumentoDto
import dev3.estouropilha.trackr.backend.enumeration.TipoCrawlerPorCodigoEnum.CORREIOS
import dev3.estouropilha.trackr.backend.helpers.BaseIntegrationTest
import dev3.estouropilha.trackr.backend.helpers.LIMPAR_TABELAS
import dev3.estouropilha.trackr.backend.models.Entrega
import dev3.estouropilha.trackr.backend.models.Movimentacao
import io.swagger.annotations.ApiModelProperty
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime.now
import java.time.LocalDateTime.of

@Sql(scripts = [LIMPAR_TABELAS], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
internal class EntregasControllerIntegrationTest : BaseIntegrationTest() {
    //TODO implementar resposta via WireMock
    @MockBean
    private lateinit var correiosCrawler: CorreiosCrawler

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
        inserirRastreioDocumento(rastreioDocumentoDto)
    }

/*    @Test
    fun `Informar CPF valido deve retornar entregas existentes contendo as vinculadas ao CPF`() {
        //TODO remover quando a resposta estiver mockada via wiremock
        `when`(correiosCrawler.consultarEntregas("1234-ABC"))
                .thenReturn(Entrega(listOf((Movimentacao("a", of(2020,10,18,17,1),
                        "b", "C")))))
        `when`(correiosCrawler.consultarEntregas("1234-ABC"))
                .thenReturn(Entrega(listOf((Movimentacao("a", of(2020,10,18,17,1),
                        "b", "C")))))

        val rastreioDocumentoDto = RastreioDocumentoDto("11111111111", "1234-ABC", CORREIOS)
        inserirRastreioDocumento(rastreioDocumentoDto)

        getMockMvc()
                .perform(get("/entregas/{cpf}", "11111111111"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].cpf").value("11111111111"))
                .andExpect(jsonPath("$[0].codigo").value("código"))
                .andExpect(jsonPath("$[0].movimentacoes[0].titulo").value("11111111111"))
                .andExpect(jsonPath("$[0].movimentacoes[0].dataHora").value("11111111111"))
                .andExpect(jsonPath("$[0].movimentacoes[0].local").value("11111111111"))
                .andExpect(jsonPath("$[0].movimentacoes[0].situacao").value("11111111111"))
    }*/

    private fun inserirRastreioDocumento(rastreioDocumentoDto: RastreioDocumentoDto) {
        getMockMvc()
                .perform(post("/entregas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(rastreioDocumentoDto)))
                .andExpect {
                    status().isCreated
                    header().string("Location", "/entregas/11111111111")
                }
    }
}