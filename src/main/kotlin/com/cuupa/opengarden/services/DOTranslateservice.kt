package com.cuupa.opengarden.services

import com.cuupa.opengarden.displayobjects.PlantDO
import com.cuupa.opengarden.displayobjects.WeatherDO
import com.cuupa.opengarden.pojos.field.Plant
import com.cuupa.opengarden.pojos.field.WaterRequirement
import com.cuupa.opengarden.services.weather.Weather

class DOTranslateservice(private val i18n: I18NService) {

    fun translate(weather: Weather): WeatherDO {
        return WeatherDO(
            condition = weather.condition?.value ?: 0,
            humidity = "",
            temperature = getFloatDisplayValue(weather.temperature, unit = celcius))
    }

    fun translate(plant: Plant): PlantDO {
        return PlantDO(
            name = plant.name ?: "-", //plant.name?.let { i18n.getMessage(it) } ?: "-",
            bionomalName = plant.bionomalName ?: "-",
            order = plant.order ?: "-",
            family = plant.family ?: "-",
            genus = plant.genus ?: "-",
            species = plant.species ?: "-",
            light = i18n.getMessage(plant.light?.name ?: "UNDEFINED"),
            light_ordinal = plant.light?.ordinal ?: -1,
            spread = getFloatDisplayValue(plant.spread, cm),
            rowSpacing = getFloatDisplayValue(plant.rowSpacing, cm),
            height = getFloatDisplayValue(plant.height, cm),
            minGarminationTemperature = getFloatDisplayValue(
                plant.minGarminationTemperature,
                celcius,
                true
            ),
            crossPolination = getCrossPolinationDisplayValue(plant.crossPolination),
            minGarminationTime = getIntDisplayValue(plant.minGarminationTime, days),
            maxGarminationTime = getIntDisplayValue(plant.maxGarminationTime, days),
            minTemperatureSingle = getFloatDisplayValue(plant.minTemperatureSingle, celcius, true),
            minTemperaturePeriod = getFloatDisplayValue(plant.minTemperaturePeriod, celcius, true),
            minIdealTemperature = getFloatDisplayValue(plant.minIdealTemperature, celcius, true),
            maxIdealTemperature = getFloatDisplayValue(plant.maxIdealTemperature, celcius, true),
            minTemperatureFruitDevelopmentDay = getFloatDisplayValue(
                plant.minIdealTemperature,
                celcius,
                true
            ),
            maxTemperatureFruitDevelopmentDay = getFloatDisplayValue(
                plant.maxTemperatureFruitDevelopmentDay,
                celcius,
                true
            ),
            minTemperatureFruitDevelopmentNight = getFloatDisplayValue(
                plant.minTemperatureFruitDevelopmentNight,
                celcius,
                true
            ),
            maxTemperatureFruitDevelopmentNight = getFloatDisplayValue(
                plant.maxTemperatureFruitDevelopmentNight,
                celcius,
                true
            ),
            frostResistent = getFrostResistentDisplayValue(plant.frostResistent),
            climbingAid = getClimbinAidDisplayValue(plant.climbingAid),
            waterRequirement = getWaterRequirementDisplayValue(plant.waterRequirements),
            waterRequirement_ordinal = plant.waterRequirements?.value ?: -1,
            waterSoil = getWaterSoilDisplayValue(plant.waterSoil),
            waterSoilBoolean = plant.waterSoil ?: true,
            waterleafs = getWaterLeafsDisplayValue(plant.waterleafs),
            waterleafsBoolean = plant.waterleafs ?: false
        )
    }

    private fun getWaterLeafsDisplayValue(waterleafs: Boolean?): String {
        return if (waterleafs == true) {
            i18n.getMessage("water_leafs")
        } else {
            i18n.getMessage("dont_water_leafs")
        }
    }

    private fun getWaterSoilDisplayValue(waterSoil: Boolean?): String {
        return if (waterSoil == true) {
            i18n.getMessage("water_soil")
        } else {
            i18n.getMessage("dont_water_soil")
        }
    }

    private fun getWaterRequirementDisplayValue(waterRequirements: WaterRequirement?): String {
        return when (waterRequirements) {
            WaterRequirement.VERY_HIGH -> i18n.getMessage("water_requirement_very_high")
            WaterRequirement.HIGH -> i18n.getMessage("water_requirement_high")
            WaterRequirement.MODERATE -> i18n.getMessage("water_requirement_moderate")
            WaterRequirement.LOW -> i18n.getMessage("water_requirement_low")
            null -> TODO()
        }
    }

    private fun getClimbinAidDisplayValue(climbingAid: Boolean?): String {
        return if (climbingAid == true) {
            i18n.getMessage("climbing_aid_needed")
        } else {
            i18n.getMessage("climbing_aid_not_needed")
        }
    }

    private fun getCrossPolinationDisplayValue(crossPolination: Boolean?): String {
        return if (crossPolination == true) {
            i18n.getMessage("cross_polination_possible")
        } else {
            i18n.getMessage("cross_polination_not_possible")
        }
    }

    private fun getFrostResistentDisplayValue(frostResistent: Boolean?): String {
        return if (frostResistent == true) {
            i18n.getMessage("is_frostresistent")
        } else {
            i18n.getMessage("is_not_frostresistent")
        }
    }

    private fun getIntDisplayValue(value: Int?, unit: String, negativeValue: Boolean = false): String {
        return if (value == null) {
            "-"
        } else if (value < 0 && !negativeValue) {
            "-"
        } else {
            if (value == 1) {
                if (unit == days) {
                    "$value ${i18n.getMessage(day)}"
                }
            }
            "$value ${i18n.getMessage(unit)}"
        }
    }

    private fun getFloatDisplayValue(value: Float?, unit: String, negativeValue: Boolean = false): String {
        return if (value == null) {
            "-"
        } else if (!negativeValue && value < 0) {
            "-"
        } else {
            "$value $unit"
        }
    }

    companion object {
        private const val celcius = "°C"
        private const val cm = " cm"
        private const val days = "days"
        private const val day = "day"
    }
}
