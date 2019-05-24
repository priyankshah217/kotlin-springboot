package com.springboot.kotlindemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients
class KotlinDemoApplication

fun main(args: Array<String>) {
    runApplication<KotlinDemoApplication>(*args)
}
