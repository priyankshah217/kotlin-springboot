package com.springboot.kotlindemo

import org.springframework.stereotype.Service

@Service
class CustomerService(val customerRepository: CustomerRepository) {

    fun createNewCustomer(customer: Customer): Customer {
        return customerRepository.save(customer)
    }

    fun getCustomers(): List<Customer> {
        return customerRepository.findAll()
    }
}