package com.example.springplayground.controller

import org.springframework.cloud.gateway.mvc.ProxyExchange
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {
    @GetMapping("/api/foo")
    fun foo(): ResponseEntity<*> =
        ResponseEntity.badRequest().body(mapOf("key" to "value"))

    @GetMapping("/proxy/api/foo")
    fun fooProxy(proxyExchange: ProxyExchange<ByteArray>): ResponseEntity<*> =
        proxyExchange.uri("$BASE_URL${proxyExchange.path("/proxy")}").get()

    companion object {
        const val BASE_URL = "http://localhost:8080"
    }
}