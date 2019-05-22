package com.springboot.kotlindemo

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Test
    fun getListCustomersFromDatabase() {
        entityManager.persistAndFlush(Customer(null, "First Name", "First Address"))
        entityManager.persistAndFlush(Customer(null, "Second Name", "Second Address"))
        entityManager.persistAndFlush(Customer(null, "Third Name", "Third Address"))
        entityManager.persistAndFlush(Customer(null, "Fourth Name", "Fourth Address"))
        assertThat(customerRepository.findAll().size, Is(equalTo(4)))
    }

    @Test
    fun createCustomerIntoDatabase() {
        val customer = Customer(null, "First Name", "First Address")
        customerRepository.save(customer)
        val savedCustomer = entityManager.persistFlushFind(customer)
        assertThat(savedCustomer.id, Is(equalTo(customer.id)))
    }
}