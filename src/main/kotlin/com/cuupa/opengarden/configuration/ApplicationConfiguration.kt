package com.cuupa.opengarden.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*


@Configuration
@Import(value = [ServiceConfiguration::class, DatabaseConfiguration::class, LocaleConfiguration::class])
@ComponentScan(basePackages = ["com.cuupa.opengarden"])
class ApplicationConfiguration