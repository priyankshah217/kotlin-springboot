package com.springboot.kotlindemo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class CustomerController {

    var quoteService: QuoteService
    var customerService: CustomerService

    @Autowired
    constructor(quoteService: QuoteService, customerService: CustomerService) {
        this.quoteService = quoteService
        this.customerService = customerService
    }

    @GetMapping("/all")
    fun allCustomers(): List<Customer> {
        return customerService.getCustomers()
    }

    @GetMapping("/quote")
    fun getQuote(): String {
        return quoteService.getQuote().quoteText
    }

    @PostMapping("/create")
    fun createCustomer(@Valid @RequestBody customer: Customer): ResponseEntity<Customer> {
        val createdCustomer: Customer? = customerService.createNewCustomer(customer)
        return ResponseEntity.ok(createdCustomer!!)
    }
}