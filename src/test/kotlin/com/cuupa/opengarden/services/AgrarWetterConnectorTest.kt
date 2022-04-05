package com.cuupa.opengarden.services

import org.junit.jupiter.api.Test

internal class AgrarWetterConnectorTest {

    @Test
    fun getWeather() {
        AgrarWetterConnector().getWeather("53111")
    }
}