package ru.elenacherev.librarymanager

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

/** Integration testing */
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class ApplicationTests(
    val restTemplate: TestRestTemplate
) {

    @Test
    fun contextLoads() {
    }
}