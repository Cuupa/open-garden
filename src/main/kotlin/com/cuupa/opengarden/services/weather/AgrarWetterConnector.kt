package com.cuupa.opengarden.services.weather

import org.apache.commons.logging.LogFactory
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class AgrarWetterConnector : WeatherConnector {

    override fun getWeather(plz: String?): Weather? {
        if (plz.isNullOrEmpty()) {
            return null
        }
        val url =
            "https://www.proplanta.de/Wetter/profi-wetter.php?SITEID=60&PLZ=$plz&WETTERaufrufen=plz&Wtp=&SUCHE=Wetter&wT="
        val document = Jsoup.connect(url)
            .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36")
            .header("Accept-Encoding", "gzip,deflate,sdch")
            .get()
        val conditions = document.select(pathCurrentConditions)
        if (conditions.isEmpty()) {
            return null
        }
        val weather = Weather()
        var index = 0
        conditions.forEach {
            when (index) {
                indexTemperature -> weather.temperature = getFloatValue(it)
                indexHumidty -> weather.humidity = getFloatValue(it)
                indexSunCondition -> weather.condition = getCondition(it)
            }
            index++
        }

        return weather
    }

    private fun getCondition(it: Element): WeatherCondition {
        val value = it.select("img").first()?.attr("title")?.split(":")?.get(1)?.trim() ?: ""
        return when (value) {
            sonnig -> WeatherCondition.FULL_SUN
            wolkig -> WeatherCondition.CLOUDY
            starkBewoelkt -> WeatherCondition.VERY_CLOUDY
            regen -> WeatherCondition.RAINY
            else -> WeatherCondition.UNDEFINED
        }
    }

    private fun getFloatValue(it: Element): Float {
        return it.text().split(" ").first().replace(",", ".").toFloat()
    }

    companion object {
        private val log = LogFactory.getLog(AgrarWetterConnector::class.java)

        const val pathCurrentConditions = ".MITTEFORMULARSPALTE_WETTER > .SCHRIFT_FORMULAR_WERTE_MITTE"
        const val indexTemperature = 2
        const val indexSunCondition = 4
        const val indexHumidty = 5

        const val wolkig = "wolkig"
        const val sonnig = "sonnig"
        const val starkBewoelkt = "stark bewölkt"
        const val regen = "regen"
    }
}