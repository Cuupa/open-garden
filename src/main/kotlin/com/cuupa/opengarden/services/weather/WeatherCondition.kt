package com.cuupa.opengarden.services.weather

enum class WeatherCondition(val value: Int) {

    FULL_SUN(6),
    CLOUDY(5),
    VERY_CLOUDY(4),
    NO_SUN(3),
    RAINY(2),
    HEAVY_RAIN(1),
    UNDEFINED(0)

}
