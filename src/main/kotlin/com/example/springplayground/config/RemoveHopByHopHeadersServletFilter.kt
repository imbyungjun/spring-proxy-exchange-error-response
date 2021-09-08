package com.example.springplayground.config

import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponseWrapper


@Component
class RemoveHopByHopHeadersServletFilter : Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        chain.doFilter(request, object : HttpServletResponseWrapper(response as HttpServletResponse) {
            override fun addHeader(name: String, value: String?) {
                if (!HEADERS_REMOVED_ON_REQUEST.contains(name.lowercase())) {
                    super.addHeader(name, value)
                }
            }
        })
    }

    companion object {
        val HEADERS_REMOVED_ON_REQUEST = setOf(
            "connection",
            "keep-alive",
            "transfer-encoding",
            "te",
            "trailer",
            "proxy-authorization",
            "proxy-authenticate",
            "x-application-context",
            "upgrade"
        )
    }

}