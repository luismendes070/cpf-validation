# Getting Started

### CPF Validation ChatGPT

Claro, vou lhe fornecer um exemplo simples de como validar um CPF utilizando Spring Boot e Kotlin. 
Vou criar um endpoint de API que recebe um CPF e retorna se ele é válido ou não.

1. Crie um novo projeto Spring Boot usando a ferramenta Spring Initializr (https://start.spring.io/) ou sua IDE preferida, incluindo as dependências "Web" e "Validation".

2. No diretório `src/main/kotlin/com/seu/pacote`, crie um novo arquivo chamado `CpfController.kt`:

```kotlin
package com.seu.pacote

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Pattern

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
```

Este código define um controlador Spring que possui um endpoint `GET /validar-cpf` que recebe um parâmetro `cpf` e utiliza validações do Spring para garantir que o formato do CPF seja válido (11 dígitos numéricos). Em seguida, ele verifica se o CPF é válido usando uma função `isValidCpf`.

3. Agora, você pode executar o projeto Spring Boot. Ao acessar `http://localhost:8080/validar-cpf?cpf=12345678909` no navegador ou em uma ferramenta como o Postman, você receberá uma resposta indicando se o CPF é válido ou não.

Lembre-se de substituir `com.seu.pacote` pelo pacote real da sua aplicação. Este exemplo é apenas uma demonstração simples de como realizar a validação de CPF. Em uma aplicação real, você pode querer adicionar mais tratamentos de erro, melhorias de código e testes para garantir o correto funcionamento.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.2/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.2/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/index.html#web)
* [Validation](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/index.html#io.validation)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

