package br.com.alura.restFull.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val filter: FilterToken
) {
    @Bean
    fun SecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
             .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/relatorio").permitAll()
            .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
            .requestMatchers(HttpMethod.POST, "/user/forgot-password").permitAll()
            .requestMatchers(HttpMethod.POST, "/user/reset-password").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun PasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun AuthenticationManager(authenticationConfiguration: AuthenticationConfiguration?): AuthenticationManager {
        return authenticationConfiguration?.authenticationManager ?: throw Exception()
    }

}