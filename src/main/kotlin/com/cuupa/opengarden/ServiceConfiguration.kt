package com.cuupa.opengarden

import com.cuupa.opengarden.services.I18NService
import com.cuupa.opengarden.services.PlantDatabase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ServiceConfiguration {

    @Bean
    open fun plantDb(): PlantDatabase {
        return PlantDatabase()
    }

    @Bean
    fun i18n() = I18NService()
}
