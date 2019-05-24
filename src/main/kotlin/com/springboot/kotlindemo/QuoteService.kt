package com.springboot.kotlindemo

import org.springframework.stereotype.Service

@Service
class QuoteService(val quoteClient: QuoteClient) {

    fun getQuote(): RandomQuoteResponse {
        return quoteClient.getRandomQuote()
    }

}
