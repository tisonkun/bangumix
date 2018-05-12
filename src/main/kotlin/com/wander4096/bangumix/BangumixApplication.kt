package com.wander4096.bangumix

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAutoConfiguration
class BangumixApplication

fun main(args: Array<String>) {
    runApplication<BangumixApplication>(*args)
}
