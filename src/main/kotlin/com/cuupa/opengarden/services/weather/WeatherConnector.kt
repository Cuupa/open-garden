package com.cuupa.opengarden.services.weather
interface WeatherConnector {

    fun getWeather(plz: String?): Weather?
}
