package com.example.demo

import main.kotlin.com.example.demo.cpf.CpfController
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@ExtendWith(MockitoExtension::class, SpringExtension::class)
@WebMvcTest(CpfController::class)
class CpfControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @InjectMocks
    private lateinit var cpfController: CpfController

    @Test
    fun testValidCpf() {
        val validCpf = "12345678909"

        mockMvc.perform(get("/validar-cpf").param("cpf", validCpf))
            .andExpect(status().isOk)
            .andExpect(content().string("CPF válido"))
    }

    @Test
    fun testInvalidCpf() {
        val invalidCpf = "12345678901"

        mockMvc.perform(get("/validar-cpf").param("cpf", invalidCpf))
            .andExpect(status().isOk)
            .andExpect(content().string("CPF inválido"))
    }

    @Test
    fun testInvalidCpfFormat() {
        val invalidCpf = "12345"

        mockMvc.perform(get("/validar-cpf").param("cpf", invalidCpf))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun testEmptyCpf() {
        val emptyCpf = ""

        mockMvc.perform(get("/validar-cpf").param("cpf", emptyCpf))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun testNullCpf() {
        mockMvc.perform(get("/validar-cpf"))
            .andExpect(status().isBadRequest)
    }
}
