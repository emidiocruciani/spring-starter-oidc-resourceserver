package com.example.resource.config

import com.example.resource.shared.auth.interceptor.BearerTokenAuthenticationInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.client.RestTemplate
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity()
class SecurityConfig(
    @Value("\${cors.allowed-origins}")
    private val allowedOrigins: String,

    @Value("\${cors.allowed-headers}")
    private val allowedHeaders: String,

    @Value("\${oauth2.introspection.url}")
    private val introspectionUrl: String,

    @Value("\${oauth2.introspection.token}")
    private val introspectionToken: String
){
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authorize ->
                authorize
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer { resourceServer ->
                resourceServer.opaqueToken { }
            }

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val cors = CorsConfiguration()
        cors.allowedOrigins = allowedOrigins.split(' ')
        cors.allowedMethods = listOf("GET", "PATCH", "POST", "PUT")
        cors.allowedHeaders = allowedHeaders.split(' ')

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", cors)

        return source
    }

    @Bean
    fun introspector(): OpaqueTokenIntrospector {
        val rest = RestTemplate()
        rest.interceptors.add(BearerTokenAuthenticationInterceptor(this.introspectionToken))

        return SpringOpaqueTokenIntrospector(this.introspectionUrl, rest)
    }
}
