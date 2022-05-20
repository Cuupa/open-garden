package com.cuupa.opengarden.configuration

import com.cuupa.opengarden.persistence.field.FieldRepository
import com.cuupa.opengarden.services.FieldDatabase
import com.cuupa.opengarden.services.PlantDatabase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PlantDBConfiguration {

    @Bean
    fun plantDb(): PlantDatabase = PlantDatabase()

    @Bean
    fun fieldDB(fieldRepository: FieldRepository): FieldDatabase = FieldDatabase(fieldRepository)
}
