package com.cuupa.opengarden.services

import com.cuupa.opengarden.services.weather.AgrarWetterConnector
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

internal class AgrarWetterConnectorTest {

    @Test
    fun getWeather() {
        val weather = AgrarWetterConnector().getWeather("53111")

        assertNotNull(weather)
        assertNotNull(weather.condition)
        assertNotNull(weather.temperature)
        assertNotNull(weather.humidity)
    }
}