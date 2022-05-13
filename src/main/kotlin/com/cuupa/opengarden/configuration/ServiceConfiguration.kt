package com.cuupa.opengarden.configuration

import com.cuupa.opengarden.persistence.field.FieldRepository
import com.cuupa.opengarden.persistence.user.UserRepository
import com.cuupa.opengarden.services.*
import com.cuupa.opengarden.services.weather.AgrarWetterConnector
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class ServiceConfiguration {


    @Bean
    fun plantDb(): PlantDatabase = PlantDatabase()

    @Bean
    fun fieldDB(fieldRepository: FieldRepository): FieldDatabase = FieldDatabase(fieldRepository)

    @Bean
    fun userDB(userRepository: UserRepository) = UserDatabase(userRepository)

    @Bean
    fun i18n() = I18NService()

    @Bean
    fun doTranslateService(i18NService: I18NService) = DOTranslateservice(i18NService)

    @Bean
    fun weatherService(geoLocationService: GeoLocationService, connector: AgrarWetterConnector) =
        WeatherService(geoLocationService, connector)


    @Value("\${application.geodata.apikey}")
    private lateinit var apikey: String

    @Bean
    fun geoLocationService() = GeoLocationService(apikey)

    @Bean
    @Primary
    fun getAgrarWetterConnector() = AgrarWetterConnector()

    @Bean
    fun cookieService() = CookieService()
}
