package com.cuupa.opengarden.services.geolocation

class GeoLocation {

    fun getMostPlausibleResult(): Result {
        return results.filter { it.confidence != null }
            .filter { it.components != null }
            .filter { it.components?.postcode != null }
            .maxByOrNull { it.confidence!! } ?: Result()
    }

    var documentation: String? = null

    var results: List<Result> = listOf()
}
