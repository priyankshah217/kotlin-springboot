package com.springboot.kotlindemo


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class RandomQuoteResponse(
        @JsonProperty("quoteAuthor")
        val quoteAuthor: String,
        @JsonProperty("quoteText")
        val quoteText: String
)