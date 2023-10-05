package com.example.resource.shared.auth.interceptor

import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse

class BearerTokenAuthenticationInterceptor(private val token: String) : ClientHttpRequestInterceptor {

    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {
        if (!request.headers.containsKey("Authorization")) {
            request.headers.add(
                "Authorization",
                "Bearer ${this.token}"
            )
        }

        return execution.execute(request, body)
    }
}
