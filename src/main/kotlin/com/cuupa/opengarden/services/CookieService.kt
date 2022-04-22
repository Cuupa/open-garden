package com.cuupa.opengarden.services

import com.cuupa.opengarden.services.geolocation.Coordinates
import javax.servlet.http.HttpServletRequest

class CookieService {

    fun getCoordinatesFromCookies(req: HttpServletRequest): Coordinates {
        val coordinates = Coordinates()

        req.cookies.iterator().forEachRemaining {
            if (it.name == "lat") {
                coordinates.lat = it.value
            } else if (it.name == "long") {
                coordinates.long = it.value
            }
        }
        return coordinates
    }
}