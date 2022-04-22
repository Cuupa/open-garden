package com.cuupa.opengarden.services

import com.cuupa.opengarden.services.geolocation.Coordinates
import com.cuupa.opengarden.services.geolocation.CoordinatesCache
import com.cuupa.opengarden.services.weather.Weather
import com.cuupa.opengarden.services.weather.WeatherConnector
import org.springframework.scheduling.annotation.Scheduled
import java.math.RoundingMode


/**
 * @author Simon Thiel (https://github.com/cuupa)
 */
class WeatherService(private val geoLocation: GeoLocationService, private val connector: WeatherConnector?) {

    private val cache = CoordinatesCache()

    fun loadWeather(coordinates: Coordinates): Weather {
        if (coordinates.valid()) {
            val temp = approximateCoordinates(coordinates)
            if (cache.notContains(temp)) {
                val location = geoLocation.getLocation(temp.lat!!, temp.long!!)
                val weather = connector?.getWeather(location.getMostPlausibleResult().components?.postcode!!)
                if (weather != null) {
                    cache.put(temp, weather)
                    return weather
                }
            } else {
                return cache.get(coordinates) ?: Weather()
            }
        }
        return Weather()
    }

    @Scheduled(fixedDelay = oneHourDelay)
    fun clean() {
        cache.clear()
    }

    private fun approximateCoordinates(coordinates: Coordinates): Coordinates {
        return Coordinates().apply {
            lat = coordinates.lat?.toBigDecimal()?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble().toString()
            long = coordinates.long?.toBigDecimal()?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble().toString()
        }
    }

    companion object {
        const val oneHourDelay = 1000L * 60L * 60L
    }
}