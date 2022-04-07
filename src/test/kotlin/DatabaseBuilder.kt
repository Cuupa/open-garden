import com.cuupa.opengarden.Light
import com.cuupa.opengarden.pojos.Plant
import com.google.gson.GsonBuilder
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

/**
 * @author Simon Thiel (https=//github.com/cuupa)
 */
class DatabaseBuilder {

    @Test
    fun fillVegetable() {
        val target = Path.of("data/vegetable.json")

        val plants = listOf(
            Plant().apply {
            name = "Tomato"
            bionomalName = "Solanum lycopersicum"
            order = "Solanales"
            family = "Solanacae"
            genus = "Solanum"
            species = "S. lycopersicum"
            light = Light.FULL_SUN
            spread = 90F
            rowSpacing = 90F
            height = 180F
            minGarminationTemperature = 15F
            crossPolination = false
            minGarminationTime = -1
            maxGarminationTime = -1
            minTemperatureSingle = 13.5F
            minTemperaturePeriod = 15F
            minIdealTemperature = 20F
            maxIdealTemperature = 25F
            minTemperatureFruitDevelopmentDay = null
            maxTemperatureFruitDevelopmentDay = 32F
            minTemperatureFruitDevelopmentNight = 13F
            maxTemperatureFruitDevelopmentNight = 24F
            frostResistent = false
        },
            Plant().apply {
                name = "Tree pepper"
                bionomalName = "Capsicum pubescens"
                order = "Solanales"
                family = "Solanacae"
                genus = "Capsicum"
                species = "TODO"
                light = Light.FULL_SUN
                spread = 50F
                rowSpacing = 50F
                height = 70F
                minGarminationTemperature = 20F
                crossPolination = false
                minGarminationTime = 20
                maxGarminationTime = 28
                minTemperatureSingle = -5F
                minTemperaturePeriod = -0.8F
                minIdealTemperature = null
                maxIdealTemperature = 15F
                minTemperatureFruitDevelopmentDay = null
                maxTemperatureFruitDevelopmentDay = null
                minTemperatureFruitDevelopmentNight = null
                maxTemperatureFruitDevelopmentNight = null
                frostResistent = null
            },
            Plant().apply {
                name = "Strawberry"
                bionomalName = "Fragaria x ananassa"
                order = "Rosales"
                family = "Rosaceae"
                genus = "Fragaria"
                species = "F. x ananassa"
                light = Light.FULL_SUN
                spread = 30F
                rowSpacing = 35F
                height = 30F
                minGarminationTemperature = 4.5F
                crossPolination = true
                minGarminationTime = 7
                maxGarminationTime = 42
                minTemperatureSingle = null
                minTemperaturePeriod = null
                minIdealTemperature = 15F
                maxIdealTemperature = 26F
                minTemperatureFruitDevelopmentDay = null
                maxTemperatureFruitDevelopmentDay = null
                minTemperatureFruitDevelopmentNight = null
                maxTemperatureFruitDevelopmentNight = null
                frostResistent = false
            })

        val gson = GsonBuilder().serializeNulls().create()

        plants.forEach {
            println(it)
        }

        val json = gson.toJson(plants)
        Files.writeString(target, json, StandardCharsets.UTF_8, StandardOpenOption.CREATE)
    }
}