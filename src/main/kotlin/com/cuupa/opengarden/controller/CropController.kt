package com.cuupa.opengarden.controller

import com.cuupa.opengarden.Light
import com.cuupa.opengarden.Search
import com.cuupa.opengarden.displayobjects.PlantDO
import com.cuupa.opengarden.pojos.Plant
import com.cuupa.opengarden.services.DOTranslateservice
import com.cuupa.opengarden.services.I18NService
import com.cuupa.opengarden.services.PlantDatabase
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import java.util.*

@Controller
class CropController(
    private val i18n: I18NService,
    private val database: PlantDatabase,
    private val doTranslateService: DOTranslateservice
) {

    @PostMapping("/search")
    fun search(@ModelAttribute search: Search?): ModelAndView {
        val result: Optional<Plant> = database.find(search?.searchTerm ?: "")
        val modelAndView = ModelAndView()

        if (result.isEmpty) {
            modelAndView.viewName = "index"
            modelAndView.addObject("success", false)
            modelAndView.addObject("text", "No plant found for search '${search?.searchTerm}'")
        } else {
            modelAndView.viewName = "redirect:/crop/${result.get().bionomalName}"
        }

        modelAndView.addObject("search", Search().apply { searchTerm = search?.searchTerm })
        return modelAndView
    }

    @GetMapping("/crop/{name}")
    fun crop(@PathVariable name: String?): ModelAndView {
        val result: Optional<Plant> = database.find(name ?: "")
        val modelAndView = ModelAndView("crop/crop")

        if (result.isEmpty) {
            modelAndView.addObject("success", false)
            modelAndView.addObject("message", "No plant found for search '${name}'")
        } else {
            modelAndView.addObject("success", true)
            modelAndView.addObject("plant", doTranslateService.translate(result.get()))
            modelAndView.fillTexts()
        }

        modelAndView.addObject("search", Search())
        return modelAndView
    }

    @GetMapping("/crop/add")
    fun add(): ModelAndView {
        return ModelAndView("crop/crop_add").apply {
            addObject("plant", PlantDO())
            addObject("search", Search())
            fillTexts()
        }
    }

    private fun ModelAndView.fillTexts() {
        addObject("bionomalName_text", i18n.getMessage("bionomalName_text"))
        addObject("name_text", i18n.getMessage("name_text"))
        addObject("minTemperatureSingle_text", i18n.getMessage("minTemperatureSingle_text"))
        addObject("order_text", i18n.getMessage("order_text"))
        addObject("genus_text", i18n.getMessage("genus_text"))
        addObject("species_text", i18n.getMessage("species_text"))
        addObject("light_text", i18n.getMessage("light_text"))
        addObject("minGarminationTemperature_text", i18n.getMessage("minGarminationTemperature_text"))
        addObject("minGarminationTime_text", i18n.getMessage("minGarminationTime_text"))
        addObject("maxGarminationTime_text", i18n.getMessage("maxGarminationTime_text"))
        addObject("minTemperaturePeriod_text", i18n.getMessage("minTemperaturePeriod_text"))
        addObject("minIdealTemperature_text", i18n.getMessage("minIdealTemperature_text"))
        addObject("maxIdealTemperature_text", i18n.getMessage("maxIdealTemperature_text"))
        addObject(
            "minTemperatureFruitDevelopmentDay_text",
            i18n.getMessage("minTemperatureFruitDevelopmentDay_text")
        )
        addObject(
            "maxTemperatureFruitDevelopmentDay_text",
            i18n.getMessage("maxTemperatureFruitDevelopmentDay_text")
        )
        addObject(
            "minTemperatureFruitDevelopmentNight_text",
            i18n.getMessage("minTemperatureFruitDevelopmentNight_text")
        )
        addObject(
            "maxTemperatureFruitDevelopmentNight_text",
            i18n.getMessage("maxTemperatureFruitDevelopmentNight_text")
        )
        addObject("frostResistent_text", i18n.getMessage("frostResistent_text"))
        addObject("crossPolination_text", i18n.getMessage("crossPolination_text"))
        addObject("submit_text", i18n.getMessage("submit"))
        addObject("light_types", i18n.getMessage(Light.values().map { it.name }))
        addObject("waterRequirement_text", i18n.getMessage("waterRequirement_text"))
        addObject("waterSoil_text", i18n.getMessage("waterSoil_text"))
        addObject("waterleafs_text", i18n.getMessage("waterleafs_text"))
        addObject("spread_text", i18n.getMessage("spread_text"))
        addObject("rowSpacing_text", i18n.getMessage("rowSpacing_text"))
        addObject("height_text", i18n.getMessage("height_text"))
    }

    @PostMapping("/crop/save")
    fun addSave(@ModelAttribute plant: PlantDO): ModelAndView {
        println(plant)
        return ModelAndView("crop/crop")
    }
}