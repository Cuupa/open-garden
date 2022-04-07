package com.cuupa.opengarden.configuration

import com.cuupa.opengarden.persistence.field.FieldRepository
import com.cuupa.opengarden.services.FieldDatabase
import com.cuupa.opengarden.services.I18NService
import com.cuupa.opengarden.services.PlantDatabase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfiguration {

    @Autowired
    var fieldRepository: FieldRepository? = null

    @Bean
    fun plantDb(): PlantDatabase = PlantDatabase()

    @Bean
    fun fieldDB(): FieldDatabase = FieldDatabase(fieldRepository)

    @Bean
    fun i18n() = I18NService()
}
