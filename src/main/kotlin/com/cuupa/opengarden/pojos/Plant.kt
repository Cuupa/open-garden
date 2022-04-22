package com.cuupa.opengarden.pojos

import com.cuupa.opengarden.Light
import com.cuupa.opengarden.displayobjects.PlantDO

data class Plant(
    var name: String? = null,
    var bionomalName: String? = null,
    var order: String? = null,
    var family: String? = null,
    var genus: String? = null,
    var species: String? = null,
    var light: Light? = null,
    var spread: Float? = null,
    var rowSpacing: Float? = null,
    var height: Float? = null,
    var minGarminationTemperature: Float? = null,
    var crossPolination: Boolean? = null,
    var minGarminationTime: Int? = null,
    var maxGarminationTime: Int? = null,
    var minTemperatureSingle: Float? = null,
    var minTemperaturePeriod: Float? = null,
    var minIdealTemperature: Float? = null,
    var maxIdealTemperature: Float? = null,
    var minTemperatureFruitDevelopmentDay: Float? = null,
    var maxTemperatureFruitDevelopmentDay: Float? = null,
    var minTemperatureFruitDevelopmentNight: Float? = null,
    var maxTemperatureFruitDevelopmentNight: Float? = null,
    var frostResistent: Boolean? = null,
    var climbingAid: Boolean? = null,
    var waterRequirements: WaterRequirement? = null,
    var waterSoil: Boolean? = null,
    var waterleafs: Boolean? = null
)