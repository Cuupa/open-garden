package com.cuupa.opengarden.configuration

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [ServiceConfiguration::class, DatabaseConfiguration::class])
@ComponentScan(basePackages = ["com.cuupa.opengarden"])
class ApplicationConfiguration {
}