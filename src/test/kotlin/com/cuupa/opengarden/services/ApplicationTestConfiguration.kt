package com.cuupa.opengarden.services

import com.cuupa.opengarden.configuration.DatabaseConfiguration
import com.cuupa.opengarden.configuration.LocaleConfiguration
import com.cuupa.opengarden.configuration.ServiceConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [ServiceConfiguration::class, DatabaseConfiguration::class, LocaleConfiguration::class])
@ComponentScan(basePackages = ["com.cuupa.opengarden"])
class ApplicationTestConfiguration {

}
