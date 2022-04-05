package com.cuupa.opengarden.controller

import com.cuupa.opengarden.Search
import com.cuupa.opengarden.pojo.Plant
import com.cuupa.opengarden.services.I18NService
import com.cuupa.opengarden.services.PlantDatabase
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import java.util.*
import javax.annotation.PostConstruct

/**
 * @author Simon Thiel (https://github.com/cuupa)
 */
@Controller
class UIController(val database: PlantDatabase, val i18n: I18NService) {

    @PostConstruct
    fun post() {
        println("Loaded UIController")
    }

    @GetMapping("/")
    fun index(): ModelAndView {
        return ModelAndView("index").apply {
            addObject("search", Search())
        }
    }

    @PostMapping("/search")
    fun search(@ModelAttribute search: Search?): ModelAndView {
        val result: Optional<Plant> = database.find(search?.searchTerm ?: "")
        val modelAndView = ModelAndView()

        if (result.isEmpty) {
            modelAndView.viewName = "search"
            modelAndView.addObject("success", false)
            modelAndView.addObject("message", "No plant found for search '${search?.searchTerm}'")
        } else {
            modelAndView.viewName = "redirect:/crop/${result.get().bionomalName}"
        }

        modelAndView.addObject("search", Search())
        return modelAndView
    }

    @GetMapping("/crop/{name}")
    fun crop(@PathVariable name: String?): ModelAndView {
        val result: Optional<Plant> = database.find(name?:"")
        val modelAndView = ModelAndView("crop")

        if (result.isEmpty) {
            modelAndView.addObject("success", false)
            modelAndView.addObject("message", "No plant found for search '${name}'")
        } else {
            modelAndView.addObject("success", true)
            modelAndView.addObject("plant", result.get())
        }

        fillTableHeadlines(modelAndView)
        modelAndView.addObject("search", Search())
        return modelAndView
    }

    private fun fillTableHeadlines(modelAndView: ModelAndView) {
        modelAndView.apply {
            addObject("name_text", i18n.get("name_text"))
            addObject("bionomalName_text", i18n.get("bionomalName_text"))
            addObject("order_text", i18n.get("order_text"))
            addObject("genus_text", i18n.get("genus_text"))
            addObject("species_text", i18n.get("species_text"))
            addObject("light_text", i18n.get("light_text"))
            addObject("minGarminationTemperature_text", i18n.get("minGarminationTemperature_text"))
            addObject("minGarminationTime_text", i18n.get("minGarminationTime_text"))
            addObject("maxGarminationTime_text", i18n.get("maxGarminationTime_text"))
            addObject("minTemperatureSingle_text", i18n.get("minTemperatureSingle_text"))
            addObject("minTemperaturePeriod_text", i18n.get("minTemperaturePeriod_text"))
            addObject("minIdealTemperature_text", i18n.get("minIdealTemperature_text"))
            addObject("maxIdealTemperature_text", i18n.get("maxIdealTemperature_text"))
            addObject("minTemperatureFruitDevelopmentDay_text", i18n.get("minTemperatureFruitDevelopmentDay_text"))
            addObject("maxTemperatureFruitDevelopmentDay_text", i18n.get("maxTemperatureFruitDevelopmentDay_text"))
            addObject("minTemperatureFruitDevelopmentNight_text", i18n.get("minTemperatureFruitDevelopmentNight_text"))
            addObject("maxTemperatureFruitDevelopmentNight_text", i18n.get("maxTemperatureFruitDevelopmentNight_text"))
            addObject("frostResistent_text", i18n.get("frostResistent_text"))
            addObject("crossPolination_text", i18n.get("crossPolination_text"))
        }
    }
}