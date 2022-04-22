package com.cuupa.opengarden.services.geolocation

class Coordinates {

    var lat: String? = null
    var long: String? = null

    fun valid() = lat != null && long != null
}
