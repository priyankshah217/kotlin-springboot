package com.springboot.kotlindemo

import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@SpringBootTest(properties = ["app.url=http://localhost:8089"])
@RunWith(SpringRunner::class)
class QuoteServiceIntegrationTest {

    @get:Rule
    var wireMockRule = WireMockRule(8089) // No-args constructor defaults to port 8080

    @Autowired
    lateinit var quoteService: QuoteService

    @Test
    fun getQuoteFromServer() {
        stubFor(get(urlEqualTo("/random"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"quoteAuthor\":\"Random Author\",\"quoteText\":\"Random Quote\"}")))
        assertThat(quoteService.getQuote().quoteText, Matchers.equalTo("Random Quote"))
    }

}