package com.cuupa.opengarden.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*

@Configuration
class LocaleConfiguration : WebMvcConfigurer {

    @Bean
    fun localeResolver() = SessionLocaleResolver().apply { setDefaultLocale(Locale.US) }

    @Bean
    fun localeChangeInterceptor() = LocaleChangeInterceptor().apply { paramName = "lang" }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeChangeInterceptor())
    }
}