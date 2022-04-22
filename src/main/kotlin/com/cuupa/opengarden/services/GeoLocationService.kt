package com.cuupa.opengarden.services

import com.cuupa.opengarden.services.geolocation.GeoLocation
import org.apache.commons.logging.LogFactory
import org.springframework.web.client.RestTemplate

class GeoLocationService(private val key: String) {

    fun getLocation(lat: String, long: String): GeoLocation {
        val request = "${requestTemplate}q=$lat%2C%20$long&key=$key"
        val response = RestTemplate().getForEntity(request, GeoLocation::class.java)
        if (response.statusCode.is2xxSuccessful) {
            return response.body!!
        }
        log.error("Unable to retrieve location")
        return GeoLocation()
    }

    companion object {
        var requestTemplate = "https://api.opencagedata.com/geocode/v1/json?"
        val log = LogFactory.getLog(GeoLocationService::class.java)
    }
}
