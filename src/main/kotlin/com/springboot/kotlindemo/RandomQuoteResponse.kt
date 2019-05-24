package com.springboot.kotlindemo


import com.fasterxml.jackson.annotation.JsonProperty

data class RandomQuoteResponse(
        @JsonProperty("quoteAuthor")
        val quoteAuthor: String,
        @JsonProperty("quoteText")
        val quoteText: String
)