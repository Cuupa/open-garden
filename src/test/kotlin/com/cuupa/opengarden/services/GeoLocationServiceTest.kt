package com.cuupa.opengarden.services

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

class GeoLocationServiceTest {

    val apiKey = Files.readString(Paths.get("src/test/resources/opencagedata-api.key"))

    @Test
    fun shouldHasResult(){
        val lat = "51"
        val long = "9"

        GeoLocationService(apiKey).getLocation(lat, long)
    }
}