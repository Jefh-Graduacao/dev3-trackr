package dev3.estouropilha.trackr.backend.controllers

import dev3.estouropilha.trackr.backend.helpers.BaseIntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

internal class PingControllerTest : BaseIntegrationTest() {

    @Test
    fun `Deve retornar Pong`() {
        getMockMvc()
                .perform(MockMvcRequestBuilders.get("/api/ping"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string("Pong"))
    }
}