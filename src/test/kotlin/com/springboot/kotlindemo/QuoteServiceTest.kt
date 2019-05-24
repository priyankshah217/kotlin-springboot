package com.springboot.kotlindemo

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class QuoteServiceTest {

    @MockK
    lateinit var quoteClient: QuoteClient

    @InjectMockKs
    lateinit var quoteService: QuoteService

    @Test
    fun getRandomQuote() {
        val expectedRandomQuoteResponse = RandomQuoteResponse("Test Author", "Random Quote")
        every { quoteClient.getRandomQuote() }.returns(expectedRandomQuoteResponse)
        assertThat(quoteService.getQuote().quoteText, equalTo(expectedRandomQuoteResponse.quoteText))
    }

}