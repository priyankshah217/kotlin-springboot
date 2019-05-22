package com.springboot.kotlindemo


import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank


@Entity
data class Customer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        @get:NotBlank
        var customerName: String = "",
        @get:NotBlank
        var customerAddress: String = ""
)