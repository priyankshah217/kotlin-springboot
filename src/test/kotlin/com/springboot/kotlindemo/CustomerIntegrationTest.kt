package com.springboot.kotlindemo

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerIntegrationTest {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    private val mapper = ObjectMapper()

    @Test
    fun getAllCustomer() {
//        Given I saved data into in-memory database
        val customer = Customer(null, "First Customer", "First Address")
        customerRepository.save(customer)

        val headers = HttpHeaders()
        val entity = HttpEntity<String>(null, headers)
        val responseEntity: ResponseEntity<String> = restTemplate.exchange("/all", HttpMethod.GET, entity,
                String::class.java)
        val responseList: List<Customer> = mapper.readValue(responseEntity.body, object :
                TypeReference<List<Customer>>() {})
        assertThat(responseList.stream().findFirst().get(), equalTo(customer))
    }

    @Test
    fun saveCustomer() {
//        Given I saved data into in-memory database
        val customer = Customer(null, "First Customer", "First Address")
        customerRepository.save(customer)

        val headers = HttpHeaders()
        val entity = HttpEntity(customer, headers)
        val responseEntity: ResponseEntity<String> = restTemplate.postForEntity("/create", entity,
                String::class.java)
        val createdCustomer: Customer = mapper.readValue(responseEntity.body, object :
                TypeReference<Customer>() {})
        assertThat(createdCustomer, equalTo(customer))
    }

    @AfterEach
    fun cleanUp() {
        customerRepository.deleteAll()
    }
}