package com.cuupa.opengarden.services

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PlantDatabaseTest {

    private val unitToTest = PlantDatabase()

    @Test
    fun find() {
        val tomato = unitToTest.find("tomato")
        assertTrue(tomato.isPresent)

        val tomato2 = unitToTest.find("Solanum lycopersicum")
        assertTrue(tomato2.isPresent)
    }

    @Test
    fun getRandomPlantName() {
        val random = unitToTest.getRandomPlantName()
        assertNotNull(random)
        assertNotEquals("", random)
    }
}