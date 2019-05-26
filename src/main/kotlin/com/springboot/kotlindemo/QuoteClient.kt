package com.springboot.kotlindemo

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping

@FeignClient(name = "QuoteClient", url = "\${app.url}")
interface QuoteClient {
    @RequestMapping("/random")
    fun getRandomQuote(): RandomQuoteResponse
}