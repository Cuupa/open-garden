package com.cuupa.opengarden.services

class I18NService {
    val values = mapOf(
        "name_text" to "Name",
        "bionomalName_text" to "Bionomal Name",
        "order_text" to "Order",
        "genus_text" to "Genus",
        "species_text" to "Species",
        "light_text" to "Light requirements",
        "minGarminationTemperature_text" to "Minimal garmination temperature",
        "minGarminationTime_text" to "Minimal garmination time",
        "maxGarminationTime_text" to "Maximal garmination time",
        "minTemperatureSingle_text" to "Minimal peak temperature",
        "minTemperaturePeriod_text" to "Minimal temperature over time period",
        "minIdealTemperature_text" to "Minimal ideal temperature",
        "maxIdealTemperature_text" to "Maximal ideal temperature",
        "minTemperatureFruitDevelopmentDay_text" to "Minimal daytime temperature for fruit development",
        "maxTemperatureFruitDevelopmentDay_text" to "Maximal daytime temperature for fruit development",
        "minTemperatureFruitDevelopmentNight_text" to "Minimal nighttime temperature for fruit development",
        "maxTemperatureFruitDevelopmentNight_text" to "Maximal nighttime temperature for fruit development",
        "frostResistent_text" to "Frost resitent",
        "crossPolination_text" to "Cross polination possible",
        "index-logo-text" to "Search for a plant or login to manage your fields and crops",
        "has-no-fields" to "You don't have any fields yet. Start by <a href='/fields/add'>creating</a> one."
    )

    fun get(s: String) = values.getOrDefault(s, "NOT FOUND")
    fun get(s: Array<? extends Enum>): String {

    }

}
