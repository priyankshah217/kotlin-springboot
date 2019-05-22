package com.springboot.kotlindemo

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasItem
import org.hamcrest.core.Is
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerServiceTest {

    @MockK
    lateinit var customerRepository: CustomerRepository

    @InjectMockKs
    lateinit var customerService: CustomerService

    @BeforeAll
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun shouldReturnAllCustomer() {
        val expectedCustomer = Customer(1, "Michael", "Mumbai")
        val customerList: List<Customer> = arrayListOf(
                Customer(1, "Michael", "Mumbai"),
                Customer(2, "John", "Bangalore"),
                Customer(3, "Ryan", "Singapore"))
        every { customerRepository.findAll() }.returns(customerList)
        val listOfCustomer = customerService.getCustomers()
        assertThat(listOfCustomer, hasItem(expectedCustomer))
    }

    @Test
    fun shouldCreateNewCustomer() {
        val customer = Customer(1, "Test Customer", "Test Address")
        every { customerRepository.save(any<Customer>()) }.returns(customer)
        val createdCustomer: Customer = customerService.createNewCustomer(customer)
        assertThat(createdCustomer.customerName, Is(equalTo(customer.customerName)))
    }
}