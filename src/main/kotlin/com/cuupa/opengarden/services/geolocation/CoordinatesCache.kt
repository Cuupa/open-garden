package com.cuupa.opengarden.services.geolocation

import com.cuupa.opengarden.services.weather.Weather

class CoordinatesCache {

    private val cache = mutableMapOf<Coordinates, Weather>()

    fun notContains(cooridantes: Coordinates): Boolean {
        return !cache.contains(cooridantes)
    }

    fun put(coordinates: Coordinates, weather: Weather) {
        cache[coordinates] = weather
    }

    fun clear() {
        cache.clear()
    }

    fun get(coordinates: Coordinates) = cache[coordinates]
}
