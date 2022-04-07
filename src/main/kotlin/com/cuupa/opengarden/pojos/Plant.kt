package com.cuupa.opengarden.pojos

import com.cuupa.opengarden.Light

class Plant {

    var name: String? = null
    var bionomalName: String? = null
    var order: String? = null
    var family: String? = null
    var genus: String? = null
    var species: String? = null
    var light: Light? = null
    var spread: Float? = null
    var rowSpacing: Float? = null
    var height: Float? = null
    var minGarminationTemperature: Float? = null
    var crossPolination: Boolean? = null
    var minGarminationTime: Int? = null
    var maxGarminationTime: Int? = null
    var minTemperatureSingle: Float? = null
    var minTemperaturePeriod: Float? = null
    var minIdealTemperature: Float? = null
    var maxIdealTemperature: Float? = null
    var minTemperatureFruitDevelopmentDay: Float? = null
    var maxTemperatureFruitDevelopmentDay: Float? = null
    var minTemperatureFruitDevelopmentNight: Float? = null
    var maxTemperatureFruitDevelopmentNight: Float? = null
    var frostResistent: Boolean? = null

    override fun toString(): String {
        return "Plant(name=$name, bionomalName=$bionomalName, order=$order, family=$family, genus=$genus, species=$species, light=$light, spread=$spread, rowSpacing=$rowSpacing, height=$height, minGarminateTemperature=$minGarminationTemperature, crossPolination=$crossPolination, minGarminationTime=$minGarminationTime, maxGarminationTime=$maxGarminationTime, minTemperatureSingle=$minTemperatureSingle, minTemperaturePeriod=$minTemperaturePeriod, minIdealTemperature=$minIdealTemperature, maxIdealTemperature=$maxIdealTemperature, minTemperatureFruitDevelopmentDay=$minTemperatureFruitDevelopmentDay, maxTemperatureFruitDevelopmentDay=$maxTemperatureFruitDevelopmentDay, minTemperatureFruitDevelopmentNight=$minTemperatureFruitDevelopmentNight, maxTemperatureFruitDevelopmentNight=$maxTemperatureFruitDevelopmentNight, frostResistent=$frostResistent)"
    }
}
