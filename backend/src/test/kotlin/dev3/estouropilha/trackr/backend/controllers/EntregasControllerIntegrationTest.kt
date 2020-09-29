package dev3.estouropilha.trackr.backend.controllers

import dev3.estouropilha.trackr.backend.helpers.BaseIntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class EntregasControllerIntegrationTest : BaseIntegrationTest() {

    @Test
    fun `Informar CPF invalido deve retornar BadRequest`() {
        getMockMvc()
                .perform(get("/entregas/{cpf}", "123456789"))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun `Informar CPF valido deve retornar entregas existentes`() {
        getMockMvc()
                .perform(get("/entregas/{cpf}", "12345678999"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[{\"movimentacoes\":[{\"dataHora\":\"2020-09-16T01:39:00\",\"local\":\"Redespacho autorizado com 1 volume e 0 Kg. Destino: RS/CACHOEIRINHA. Previsao de entrega: 18/09/20.\",\"situacao\":\"CONTAGEM / MG RED CON\"},{\"dataHora\":\"2020-09-16T04:27:00\",\"local\":\"Saida da unidade CONTAGEM em 16/09/20, 04:27h. Previsao de chegada na unidade JUNDIAI em 16/09/20, 14:26h.\",\"situacao\":\"CONTAGEM / MG RED CON\"},{\"dataHora\":\"2020-09-16T14:20:00\",\"local\":\"Chegada na unidade JUNDIAI em 16/09/20, 14:20h.\",\"situacao\":\"JUNDIAI / SP RED SPO\"},{\"dataHora\":\"2020-09-17T01:41:00\",\"local\":\"Saida da unidade JUNDIAI em 17/09/20, 01:41h. Previsao de chegada na unidade GRAVATAI em 17/09/20, 21:39h.\",\"situacao\":\"JUNDIAI / SP RED SPO\"},{\"dataHora\":\"2020-09-17T22:24:00\",\"local\":\"Chegada na unidade GRAVATAI em 17/09/20, 22:24h.\",\"situacao\":\"GRAVATAI / RS RED POT\"},{\"dataHora\":\"2020-09-18T09:42:00\",\"local\":\"Saida para entrega na cidade CACHOEIRINHA.\",\"situacao\":\"GRAVATAI / RS RED POT\"},{\"dataHora\":\"2020-09-18T13:18:00\",\"local\":\"ENTREGUE EM 18/09/20 13:18H RECEBIDO POR DENIS RENATO, PORTEIRO DO DESTINATARIO - INFORMADA ATRAVES DA CHAVE **********************0020009606991082023928 - MOBILE 5 DOC N.: *****70264.\",\"situacao\":\"GRAVATAI / RS RED POT\"}]}]"))
    }
}