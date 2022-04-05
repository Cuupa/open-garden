package com.cuupa.opengarden

import com.cuupa.opengarden.pojo.Plant
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import java.util.*

/**
 * @author Simon Thiel (https://github.com/cuupa)
 */
@Controller
class UIController {

    @GetMapping("/")
    fun index(): ModelAndView {
        return ModelAndView("index")
    }

    @PostMapping("/")
    fun search(@ModelAttribute searchTerm: String): ModelAndView {
        val result: Optional<Plant> = database.find(searchTerm)
        val modelAndView = ModelAndView("search")

        if (result.isEmpty) {
            modelAndView.addObject("success", false)
            modelAndView.addObject("message", "No plant found for search '$searchTerm'")
        } else {
            modelAndView.addObject("success", true)
            modelAndView.addObject("plant", result.get())
        }

        return modelAndView
    }
}