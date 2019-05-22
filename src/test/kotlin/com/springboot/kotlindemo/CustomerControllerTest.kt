package com.springboot.kotlindemo

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*


@WebMvcTest
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
class CustomerControllerTest {

    @MockkBean
    lateinit var customerService: CustomerService

    @Autowired
    lateinit var mockMvc: MockMvc

    private val mapper = ObjectMapper()

    @Test
    fun getAllCustomer() {
        val customer1 = Customer(1, "mvc name 1", "mvc address 1")
        val customer2 = Customer(2, "mvc name 2", "mvc address 2")
        every { customerService.getCustomers() }.returns(Arrays.asList(customer1, customer2))
        mockMvc.perform(
                get("/all")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk)
                .andExpect(jsonPath("$[0].id", Is(equalTo(customer1.id))))
                .andExpect(jsonPath("$[0].customerName", Is(equalTo(customer1.customerName))))
                .andExpect(jsonPath("$[0].customerAddress", Is(equalTo(customer1.customerAddress))))
                .andExpect(jsonPath("$[1].id", Is(equalTo(customer2.id))))
                .andExpect(jsonPath("$[1].customerName", Is(equalTo(customer2.customerName))))
                .andExpect(jsonPath("$[1].customerAddress", Is(equalTo(customer2.customerAddress))))
    }

    @Test
    fun createNewCustomer() {
        val customer = Customer(1, "mvc name 1", "mvc address 1")
        every { customerService.createNewCustomer(any()) }.returns(customer)
        val requestBody = mapper.writeValueAsString(customer)
        mockMvc.perform(
                post("/create")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestBody)
        ).andExpect(jsonPath("$.id", Is(equalTo(customer.id))))
                .andExpect(jsonPath("$.customerName", Is(equalTo(customer.customerName))))
                .andExpect(jsonPath("$.customerAddress", Is(equalTo(customer.customerAddress))))
                .andExpect(status().isOk)
    }
}