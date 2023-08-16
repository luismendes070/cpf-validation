package main.kotlin.com.example.demo.cpf

import org.intellij.lang.annotations.Pattern
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
// import javax.validation.constraints.Pattern

@RestController
class CpfController {

    @GetMapping("/validar-cpf")
    fun validarCpf(@RequestParam @Pattern(regexp = "\\d{11}") cpf: String): String {
        if (isValidCpf(cpf)) {
            return "CPF válido"
        }
        return "CPF inválido"
    }

    private fun isValidCpf(cpf: String): Boolean {
        if (cpf.length != 11) {
            return false
        }

        val digits = cpf.map { it.toString().toInt() }

        val firstDigit = (0..8).sumOf { (digits[it] * (10 - it)) } % 11
        val secondDigit = (0..9).sumOf { (digits[it] * (11 - it)) } % 11

        val calculatedDigits = listOf(firstDigit, secondDigit).map { if (it < 10) it else 0 }

        return calculatedDigits == digits.takeLast(2)
    }
}
