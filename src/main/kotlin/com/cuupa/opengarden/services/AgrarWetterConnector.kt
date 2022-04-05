package com.cuupa.opengarden.services

import org.springframework.web.client.RestTemplate

class AgrarWetterConnector {

    fun getWeather(plz: String): Weather?{
        val restTemplate = RestTemplate()
//        val response = restTemplate.getForEntity("https://www.proplanta.de/Wetter/profi-wetter.php?SITEID=60&PLZ=$plz&WETTERaufrufen=stadt&Wtp=&SUCHE=Wetter&wT=", String::class.java )

//        print(response)
        return null
    }
}