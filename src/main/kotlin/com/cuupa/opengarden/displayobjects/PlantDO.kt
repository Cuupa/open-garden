package com.cuupa.opengarden.displayobjects

data class PlantDO(
    var name: String = "",
    var bionomalName: String = "",
    var order: String = "",
    var family: String = "",
    var genus: String = "",
    var species: String = "",
    var light: String = "",
    var light_ordinal: Int = -1,
    var spread: String = "",
    var rowSpacing: String = "",
    var height: String = "",
    var minGarminationTemperature: String = "",
    var crossPolination: String = "",
    var minGarminationTime: String = "",
    var maxGarminationTime: String = "",
    var minTemperatureSingle: String = "",
    var minTemperaturePeriod: String = "",
    var minIdealTemperature: String = "",
    var maxIdealTemperature: String = "",
    var minTemperatureFruitDevelopmentDay: String = "",
    var maxTemperatureFruitDevelopmentDay: String = "",
    var minTemperatureFruitDevelopmentNight: String = "",
    var maxTemperatureFruitDevelopmentNight: String = "",
    var frostResistent: String = ""
) {
}
